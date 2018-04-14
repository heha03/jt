import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	private static XSSFSheet readExcel(){
		XSSFWorkbook wb=null;
		try{
			File file=new File("D:\\input.xlsx");	
			OPCPackage pkg=OPCPackage.open(file.getAbsolutePath());
			wb=new XSSFWorkbook(pkg);
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return wb.getSheetAt(0);
	}
	private static String toPlainString(String s){
		return new BigDecimal(s).toPlainString();
	}
	@SuppressWarnings("finally")
	public static User[] getUserURL(){
		XSSFSheet sh=readExcel();
		User[]ret=new User[sh.getLastRowNum()+1];
		int cnt=0;
		try{
			for(Iterator<Row> rowIterator=sh.iterator();rowIterator.hasNext();){
				XSSFRow row=(XSSFRow)rowIterator.next();
				String userName="",url="";
				for(Iterator<Cell> iterator=row.cellIterator();iterator.hasNext();){
					XSSFCell cell=(XSSFCell)iterator.next();
					String cellContent=cell.toString();
					if(cellContent.length()>0&&cellContent.charAt(0)=='3')userName=toPlainString(cellContent);
					else url=cellContent;
				}
				ret[cnt++]=new User(userName, url);
				System.out.println(userName+":"+url);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			return ret;
		}
	}
	public static void main(String[]args){
//		try{
//			XSSFSheet sh=readExcel();
//			for(Iterator<Row> rowIterator=sh.iterator();rowIterator.hasNext();){
//				XSSFRow row=(XSSFRow)rowIterator.next();
//				for(Iterator<Cell> iterator=row.cellIterator();iterator.hasNext();){
//					XSSFCell cell=(XSSFCell)iterator.next();
//					String outs=cell.toString();
//					if(outs.length()>0&&outs.charAt(0)=='3')System.out.print(toPlainString(outs));
//					else System.out.print(cell);
//				}System.out.println();
//			}
//		}catch (Exception e){
//			e.printStackTrace();
//		}
		getUserURL();
	}
}
