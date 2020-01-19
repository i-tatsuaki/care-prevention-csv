package com.tatsuaki.carepreventioncsv.controller

import com.tatsuaki.carepreventioncsv.config.LoginUserConfig
import com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsv
import com.tatsuaki.carepreventioncsv.model.service.csvchecker.CsvCheckerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.util.MimeTypeUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile

import javax.servlet.http.HttpServletResponse
import java.io.IOException
import java.io.PrintWriter
import java.util.ArrayList

@Controller
class CsvCheckerController @Autowired
constructor(internal var csvCheckerService: CsvCheckerService) {

    @GetMapping
    fun initCsvChecker(): String {
        return "csvchecker/importcsv"
    }

    @PostMapping("import")
    fun upload(@RequestParam("upload_file") file: MultipartFile, model: Model): String {
        // model.addAttribute("originalFilename", multipartFile.getOriginalFilename());

        val list = ArrayList<String>()
        for (i in 0..2) {
            val w = Integer.toString(i)
            list.add(w)
        }
        model.addAttribute("list", list)

        val carePreventionCsv = csvCheckerService.check(file)
        model.addAttribute("csv", carePreventionCsv)
        return "csvchecker/result"
    }

    @GetMapping("import/download")
    fun download(response: HttpServletResponse) {

        //文字コードと出力するCSVファイル名を設定
        response.contentType = MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8"
        // TODO ファイル名を記載する
        response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"")

        try {
            response.writer.use { printWriter ->
                // TODO csvの内容を書く
                printWriter.print("test")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
