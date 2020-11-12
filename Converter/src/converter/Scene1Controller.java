/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;


import com.aspose.words.Document;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;


import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 * FXML Controller class
 *
 * @author deepp
 */
public class Scene1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    List<String> lstFile; 
    @FXML
    private Label lblString;
    
    @FXML
    private Label filePath;
    
    
    public File f ;
    
    public void browseButtnClicked(ActionEvent event) throws IOException{
        filePath.setVisible(false);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Files","*.*"));
        f = fc.showOpenDialog(null);
        if(f!= null){
            filePath.setVisible(true);
            lblString.setText(f.getCanonicalPath());
        }
        
        //Testing converter code:
       XWPFDocument doc = new XWPFDocument();

		String pdf = lblString.getText();
		PdfReader reader = new PdfReader(pdf);
		PdfReaderContentParser parser = new PdfReaderContentParser(reader);

		for (int i = 1; i <= reader.getNumberOfPages(); i++) {
			TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
			String text = strategy.getResultantText();
			XWPFParagraph p = doc.createParagraph();
			XWPFRun run = p.createRun();
			run.setText(text);
			run.addBreak(BreakType.PAGE);
		}
		FileOutputStream out = new FileOutputStream(pdf+".docx");
		doc.write(out);
		out.close();
		reader.close();
		doc.close();

   
    }
    public void pdfButtonClicked(ActionEvent event) throws IOException {
        Parent pdftodox = FXMLLoader.load(getClass().getResource("PDFtoWord.fxml"));
        Scene pdfscene = new Scene(pdftodox);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(pdfscene);
        window.show();
    }
    public void backButtonClicked(ActionEvent event) throws IOException{
        Parent formtohome = FXMLLoader.load(getClass().getResource("Main Screen.fxml"));
        Scene homescene = new Scene(formtohome);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homescene);
        window.show();
    }
    public void splashscreenClicked(MouseEvent event) throws IOException{
        Parent splash = FXMLLoader.load(getClass().getResource("Main Screen.fxml"));
        Scene spl = new Scene(splash);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(spl);
        window.show();
    }
    public void doxButtnClicked(ActionEvent event) throws IOException{
        Parent doxtopdf = FXMLLoader.load(getClass().getResource("WordToPDF.fxml"));
        Scene dox = new Scene(doxtopdf);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(dox);
        window.show();
    }
    
    public void pngToJpg(ActionEvent event) throws IOException{
        Parent pngtojpg = FXMLLoader.load(getClass().getResource("PngtoJpg.fxml"));
        Scene png = new Scene(pngtojpg);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(png);
        window.show();
    }
    
    public void doxConverter(ActionEvent event) throws IOException, Exception{
       filePath.setVisible(false);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Files","*.*"));
        f = fc.showOpenDialog(null);
        if(f!= null){
            filePath.setVisible(true);
            lblString.setText(f.getCanonicalPath());
        }
        Document doc = new Document(lblString.getText());
        FileOutputStream ff = new FileOutputStream(f+".pdf");
        doc.save(f+".pdf");
        
    }
    public void pngToConverter(ActionEvent event)throws IOException,Exception{
         

            filePath.setVisible(false);
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("Files","*.*"));
        f = fc.showOpenDialog(null);
        if(f!= null){
            filePath.setVisible(true);
            lblString.setText(f.getCanonicalPath());
        }
        String name = f.getCanonicalPath();
        String oname = f.getCanonicalPath()+".jpg";
             File input = new File(name);
            File output = new File(oname);
            BufferedImage image = ImageIO.read(input);
            BufferedImage result = new BufferedImage(
                    image.getWidth(),
                    image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            result.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
            
            
            ImageIO.write(result, "jpg",output);
//            if(oname!=null){
//                JOptionPane.showMessageDialog(null,"The File is at Path:"+oname+"");
//            }
        
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        
        
    }    
    
}
