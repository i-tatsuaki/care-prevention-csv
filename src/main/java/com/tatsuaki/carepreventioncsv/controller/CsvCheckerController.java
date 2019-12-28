package com.tatsuaki.carepreventioncsv.controller;

import com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsv;
import com.tatsuaki.carepreventioncsv.model.service.csvchecker.CsvCheckerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CsvCheckerController {

    CsvCheckerService csvCheckerService;

    @Autowired
    public CsvCheckerController(CsvCheckerService csvCheckerService) {
        this.csvCheckerService = csvCheckerService;
    }

    @GetMapping
    public String initCsvChecker() {
        return "csvchecker/importcsv";
    }

    @PostMapping("import")
    public String upload(@RequestParam("upload_file") MultipartFile file, Model model) {
        // model.addAttribute("originalFilename", multipartFile.getOriginalFilename());

        List<String> list = new ArrayList<>();
        for(int i = 0; i < 3; i++) {
            String w = Integer.toString(i);
            list.add(w);
        }
        model.addAttribute("list", list);

        CarePreventionCsv carePreventionCsv = csvCheckerService.check(file);
        model.addAttribute("csv", carePreventionCsv);
        return "csvchecker/result";
    }

    @GetMapping("import/download")
    public void download(HttpServletResponse response) {

        //文字コードと出力するCSVファイル名を設定
        response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
        // TODO ファイル名を記載する
        response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");

        try(PrintWriter printWriter = response.getWriter()) {
            // TODO csvの内容を書く
            printWriter.print("test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
