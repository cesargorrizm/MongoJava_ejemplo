package com.becaries.MongoJava.Clases;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class GenerarTabla {
    private static int PADDING_SIZE = 2;
	static String NEW_LINE = "\n";
	private static String TABLE_JOINT_SYMBOL = "+";
	private static String TABLE_V_SPLIT_SYMBOL = "|";
	private static String TABLE_H_SPLIT_SYMBOL = "-";

    public static String generateTable(List<String> headersList, List<List<String>> rowsList,
            int... overRiddenHeaderHeight) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.setLength(0);
        int rowHeight = overRiddenHeaderHeight.length > 0 ? overRiddenHeaderHeight[0] : 1;
        Map<Integer, Integer> columnMaxWidthMapping = getMaximumWidhtofTable(headersList, rowsList);
        columnMaxWidthMapping = getMaximumWidhtofTable(headersList, rowsList);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        stringBuilder.append(NEW_LINE);
        for (int headerIndex = 0; headerIndex < headersList.size(); headerIndex++) {
            fillCell(stringBuilder, headersList.get(headerIndex), headerIndex, columnMaxWidthMapping);
        }
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        for (List<String> row : rowsList) {
            for (int i = 0; i < rowHeight; i++) {
                stringBuilder.append(NEW_LINE);
            }
            for (int cellIndex = 0; cellIndex < row.size(); cellIndex++) {
                fillCell(stringBuilder, row.get(cellIndex), cellIndex, columnMaxWidthMapping);
            }
        }
        stringBuilder.append(NEW_LINE);
        createRowLine(stringBuilder, headersList.size(), columnMaxWidthMapping);
        stringBuilder.append(NEW_LINE);
        stringBuilder.append(NEW_LINE);
        String pasar = stringBuilder.toString();

        headersList.clear();
        rowsList.clear();
        stringBuilder.setLength(0);
        overRiddenHeaderHeight = null;
        columnMaxWidthMapping.clear();

        return pasar;
    }

    static void fillSpace(StringBuilder stringBuilder, int length) {
        for (int i = 0; i < length; i++) {
            stringBuilder.append(" ");
        }
    }

    public static void createRowLine(StringBuilder stringBuilder, int headersListSize,
            Map<Integer, Integer> columnMaxWidthMapping) {
        for (int i = 0; i < headersListSize; i++) {
            if (i == 0) {
                stringBuilder.append(TABLE_JOINT_SYMBOL);
            }
            for (int j = 0; j < columnMaxWidthMapping.get(i) + PADDING_SIZE * 2; j++) {
                stringBuilder.append(TABLE_H_SPLIT_SYMBOL);
            }
            stringBuilder.append(TABLE_JOINT_SYMBOL);
        }
    }

    public static Map<Integer, Integer> getMaximumWidhtofTable(List<String> headersList, List<List<String>> rowsList) {
        Map<Integer, Integer> columnMaxWidthMapping = new HashMap<>();
        columnMaxWidthMapping.clear();
        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            columnMaxWidthMapping.put(columnIndex, 0);
        }
        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            if (headersList.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
                columnMaxWidthMapping.put(columnIndex, headersList.get(columnIndex).length());
            }
        }
        for (List<String> row : rowsList) {
            for (int columnIndex = 0; columnIndex < row.size(); columnIndex++) {
                if (row.get(columnIndex).length() > columnMaxWidthMapping.get(columnIndex)) {
                    columnMaxWidthMapping.put(columnIndex, row.get(columnIndex).length());
                }
            }
        }
        for (int columnIndex = 0; columnIndex < headersList.size(); columnIndex++) {
            if (columnMaxWidthMapping.get(columnIndex) % 2 != 0) {
                columnMaxWidthMapping.put(columnIndex, columnMaxWidthMapping.get(columnIndex) + 1);
            }
        }
        return columnMaxWidthMapping;
    }

    public static int getOptimumCellPadding(int cellIndex, int datalength, Map<Integer, Integer> columnMaxWidthMapping,
            int cellPaddingSize) {
        if (datalength % 2 != 0) {
            datalength++;
        }
        if (datalength < columnMaxWidthMapping.get(cellIndex)) {
            cellPaddingSize = cellPaddingSize + (columnMaxWidthMapping.get(cellIndex) - datalength) / 2;
        }
        return cellPaddingSize;
    }

    public static void fillCell(StringBuilder stringBuilder, String cell, int cellIndex,
            Map<Integer, Integer> columnMaxWidthMapping) {
        int cellPaddingSize = getOptimumCellPadding(cellIndex, cell.length(), columnMaxWidthMapping, PADDING_SIZE);
        if (cellIndex == 0) {
            stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
        }
        GenerarTabla.fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(cell);
        if (cell.length() % 2 != 0) {
            stringBuilder.append(" ");
        }
        GenerarTabla.fillSpace(stringBuilder, cellPaddingSize);
        stringBuilder.append(TABLE_V_SPLIT_SYMBOL);
    }

}