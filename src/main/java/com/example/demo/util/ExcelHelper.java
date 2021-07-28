package com.example.demo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.Stock;

public class ExcelHelper {

	public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	static String SHEET = "stocks";

	public static boolean hasExcelFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public static List<Stock> excelToStocks(InputStream is) 
	{
		try 
		{
			Workbook workbook = new XSSFWorkbook(is);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.iterator();

			List<Stock> stocks = new ArrayList<Stock>();

			int rowNumber = 0;

			while (rows.hasNext()) {

				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();

				Stock stock = new Stock();

				int cellIdx = 0;

				while (cellsInRow.hasNext()) {

					Cell currentCell = cellsInRow.next();

					switch (cellIdx) {

					case 0:
						stock.setCompanyId((int) currentCell.getNumericCellValue());
						break;

					case 1:
						stock.setExchangeId((int) currentCell.getNumericCellValue());
						break;

					case 2:
						stock.setPrice((int) currentCell.getNumericCellValue());
						break;

					case 3:
						stock.setDate(currentCell.getStringCellValue());
						break;

					case 4:
						stock.setTime(currentCell.getStringCellValue());
						break;

					default:
						break;

					}

					cellIdx++;
				}

				stocks.add(stock);
			}

			workbook.close();

			return stocks;

		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}
