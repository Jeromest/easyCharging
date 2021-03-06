package com.song.service;

import com.song.mapper.IntentionMapper;
import com.song.entity.Intention;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



@Service
public class IntentionService {

    @Autowired
    private IntentionMapper intentionMapper;

    public int create(Intention intention) {
        return intentionMapper.create(intention);
    }

    public int delete(String ids) {
        String[] arr = ids.split(",");
        int row = 0;
        for (String s : arr) {
            if(!StringUtils.isEmpty(s)){
                intentionMapper.delete(Integer.parseInt(s));
            row++;
            }
        }
        return row;
    }

    public int delete(Integer id) {
        return intentionMapper.delete(id);
    }

    public int update(Intention intention) {
        return intentionMapper.update(intention);
    }

    public int updateSelective(Intention intention) {
        return intentionMapper.updateSelective(intention);
    }

    public PageInfo<Intention> query(Intention intention) {
        if(intention != null && intention.getPage() != null){
            PageHelper.startPage(intention.getPage(),intention.getLimit());
        }
        return new PageInfo<Intention>(intentionMapper.query(intention));
    }

    public Intention detail(Integer id) {
        return intentionMapper.detail(id);
    }

    public int count(Intention intention) {
        return intentionMapper.count(intention);
    }


    public int addToDatabase(MultipartFile file) throws Exception{
        int result = 0;
        List<Intention> intentionList = new ArrayList<>();                    // ??????billList

        String fileName = file.getOriginalFilename();                           // ????????????????????????
        String suffix = fileName.substring(fileName.lastIndexOf(".")+1);    // ????????????????????????

        System.out.println("??????????????????" + suffix);
        InputStream inputStream = file.getInputStream();            // ?????????????????????
        Workbook workbook = null;                                   // 1. ???????????????

        if(suffix.equals("xlsx")){
            workbook = new XSSFWorkbook(inputStream);               // Excel2007??????????????? ???XSSF(???Poi-ooxml)??????
        } else {
            workbook = new HSSFWorkbook(inputStream);               // Excel2003?????? ???HSSF(???Poi)??????
        }

        Sheet sheet = workbook.getSheetAt(0);                     // 2. ?????????????????????

        if(sheet != null){
            for (int i = 1; i <= sheet.getLastRowNum(); i++){       // 3. ?????????????????????????????????????????????
                Row row = sheet.getRow(i);
                if (row != null){
                    List<String> list = new ArrayList<>();
                    for (Cell cell : row){                          // 4. ????????????????????????
                        if (cell != null) {
                            cell.setCellType(Cell.CELL_TYPE_STRING);        // ?????????????????????????????????
                            String cellValue = cell.getStringCellValue();   // ???????????????????????????
                            list.add(cellValue);                            // ??????????????????
                        }
                    }

                    // ????????????
                    Integer id = Integer.parseInt(list.get(0));
                    String stuName = list.get(1);
                    Integer gender = Integer.parseInt(list.get(2));
//                    Integer lateNNoise = Integer.parseInt(list.get(3));
                    Integer late = Integer.parseInt(list.get(3));
                    Integer noise = Integer.parseInt(list.get(4));

                    int lateNNoise = 0;
                    if (late == 0){
                        if (noise == 0) {
                            lateNNoise = 0;
                        } else {
                            lateNNoise = 1;
                        }
                    }else if (late == 1){
                        if (noise == 0) {
                            lateNNoise = 2;
                        } else {
                            lateNNoise = 3;
                        }
                    }

                    // ?????????????????????????????????????????????????????????????????????
                    Intention intention = new Intention(id, stuName, gender, late, noise, lateNNoise);
                    System.out.println("????????????"+ i +"??????????????????");
                    System.out.println("----------");
                    System.out.println(intention);
                    intentionList.add(intention);                                 // ???????????????????????????billList
                }
            }

            for (Intention intention: intentionList){
                result = intentionMapper.addIntentionExcelFileToDatabase(intention);      // ?????????????????????????????????
            }
        }
        return result;
    }



    public int queryMaleAmount(){
        return intentionMapper.queryMaleAmount();
    }

    public int queryFemaleAmount(){
        return intentionMapper.queryFemaleAmount();
    }


    public List<Integer> queryMaleIdByType(){
        return intentionMapper.queryMaleIdByType();
    }
}

