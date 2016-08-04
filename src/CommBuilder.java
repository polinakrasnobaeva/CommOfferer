import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBException;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.Text;

public class CommBuilder {
	
	private String templatePath = "";
	private String tempXMLPath = "";
	private final String picFolder = "." + File.separator + "config" + File.separator + "regions" + File.separator + "pic" + File.separator;
	
	public CommBuilder(String templatePath, String tempXMLPath){
		this.templatePath = templatePath;
		this.tempXMLPath = tempXMLPath;
	}
	
	public int assemble(String csvPath, String resultPath, HashMap<String, String> values, String regions) {
		long startTime = System.currentTimeMillis();
		
		
		//копируем шаблон
		Path tempPath = Paths.get(templatePath);
		Path resultikPath = Paths.get(resultPath);
	    try {
			Files.copy(tempPath, resultikPath, REPLACE_EXISTING);
		} catch (IOException e3) {
			LOG("Не удалось скопировать файл-шаблон");
			e3.printStackTrace();
			return 1;
		}
	    
	    //подменяем картинку менеджера
	    ZipReplacer.zipFileReplace(resultPath, "word/media/image9.jpeg", picFolder + values.get("placeholdermanagername") + ".jpeg");
	    
		//подставляем переменные замены, понятные для docx4j
		ZipReplacer.getFileFromZip(resultPath, "word/document.xml", tempXMLPath); //достает document.xml из docx
		try {
			ZipReplacer.regexpReplacer(tempXMLPath, "<w:t>(placeholder.*?)</w:t>", "<w:t>\\$\\{$1\\}</w:t>");
		} catch (IOException e2) {
			LOG("Не удалось осуществить приведение переменных к нужному виду");
			e2.printStackTrace();
			return 1;
		}
		ZipReplacer.zipFileReplace(resultPath, "word/document.xml", tempXMLPath);
		
		//ссылочки в конце
		ZipReplacer.getFileFromZip(resultPath, "word/_rels/document.xml.rels", tempXMLPath.replace("xml", "xml.rels")); //достает document.xml.rels из docx
		try {
			ZipReplacer.regexpReplacer(tempXMLPath.replace("xml", "xml.rels"), "//дельта-юг.рф", "//" + values.get("placeholderdeltasite").replace("www.", ""));
		} catch (IOException e2) {
			LOG("Не удалось заменить ссылки на кнопках");
			e2.printStackTrace();
			return 1;
		}
		ZipReplacer.zipFileReplace(resultPath, "word/_rels/document.xml.rels", tempXMLPath.replace("xml", "xml.rels"));
		

		//считываем CSV с таблицей фраз из подбора
		CSVReader csvReader = new CSVReader(csvPath);
		
		Table table = new Table();
		Line line;
		while((line = csvReader.readLine()) != null){
			table.addLine(line);
		}
		csvReader.close();
		
		//открываем скопированный файл шаблона 
		DOCXworker dw = null;
		try {
			System.out.println(templatePath + "\n" + resultPath);
			dw = new DOCXworker(resultPath, resultPath);
		} catch (Docx4JException e) {
			LOG("Не удалось создать DOCXWorker - Docx4JException");
			e.printStackTrace();
			return 1;
		} catch (JAXBException e) {
			LOG("Не удалось создать DOCXWorker - JAXBException");
			e.printStackTrace();
			return 1;
		}
		//вставляем таблицу
		for(int i = 0; i < table.getSize(); i++){
			if(i == table.getSize() - 1){
				dw.addLastRowToTable(table.getLine(i));
			}else{
				dw.addRowToTable(table.getLine(i), (i % 2 == 0));
			}
		}
		//указываем переменные подстановки
		
	    //ссылки
	    List<Object> hls = DOCXworker.getAllElementFromObject(dw.getMainDocPart(), Hyperlink.class);
	    Relationship rel = dw.getMainDocPart().getRelationshipsPart().getRelationshipByID(((Hyperlink) hls.get(0)).getId());
	    rel.setTarget("mailto:" + values.get("placeholderemail"));
	    //аудитория
	    List<Object> lt = DOCXworker.getAllElementFromObject(dw.templateTable, Text.class);
	    values.put("placeholderauditory", ((Text)lt.get(lt.size()-1)).getValue());
	    //регион
	    String[] regionArray = regions.split("\n");  
	    if(regionArray.length > 0){
	    	P regionParagraph = null;
		    try {
				regionParagraph = dw.findByPlaceholderParagraph(DOCXworker.getAllElementFromObject(dw.getMainDocPart(), P.class), "phregion");
			} catch (Docx4JException | JAXBException e1) {
				LOG("Не удалось найти параграф-шаблон для регионов");
				e1.printStackTrace();
				return 1;
			}
		    int regionParagraphIndex = dw.getMainDocPart().getContent().indexOf(regionParagraph) + 1;
		    Text t = (Text) DOCXworker.getAllElementFromObject(regionParagraph, Text.class).get(0);
		    t.setValue(regionArray[0]);
		    for(int i = 1; i < regionArray.length; i++){
		    	P p = (P) XmlUtils.deepCopy(regionParagraph);
		    	t = (Text) DOCXworker.getAllElementFromObject(p, Text.class).get(0);
		    	t.setValue(regionArray[i]);
		    	dw.getMainDocPart().getContent().add(regionParagraphIndex++, p);
		    }
	    }
	    //футер
	    List<Object> footerContent = dw.getFooterPart().getContent();
	    Text t = (Text) DOCXworker.getAllElementFromObject(footerContent.get(2), Text.class).get(0);
	    t.setValue(values.get("placeholderaddress"));
	    t = (Text) DOCXworker.getAllElementFromObject(footerContent.get(3), Text.class).get(0);
	    t.setValue(values.get("placeholderphonenumbers"));
	    t = (Text) DOCXworker.getAllElementFromObject(footerContent.get(4), Text.class).get(0);
	    t.setValue(values.get("placeholderdeltasite"));
	    //запускаем замену
		try {
			dw.getMainDocPart().variableReplace(values);
		} catch (JAXBException | Docx4JException e) {
			LOG("Не удалось подставить переменные");
			e.printStackTrace();
			return 1;
		}
		//сохраняем
		dw.writeDocx();
		System.out.println("Готово; " + (System.currentTimeMillis() - startTime) + " мс.");
		return 0;
		
	}
	
	private static void LOG(String message){
		System.out.println("----------\n" + message + "\n----------");
	}
}
