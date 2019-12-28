package com.tatsuaki.carepreventioncsv.model.domain;

import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CarePreventionCsv {

    final static public List<String> HEADER = Arrays.asList(
            "証記載保険者番号",
            "サービス種類コード",
            "サービス項目コード",
            "適用開始年月",
            "適用終了年月",
            "サービス名称",
            "単位数",
            "算定単位",
            "制限日数・回数",
            "算定回数制限期間",
            "支給限度額対象区分",
            "予備項目",
            "給付率",
            "利用者負担額",
            "事業対象者実施区分",
            "要支援１受給者実施区分",
            "要支援２受給者実施区分",
            "国保連合会委託区分",
            "作成年月日"
    );

    private List<CarePreventionCsvItem> items;
    private String encode;
    private boolean hasBom;
    private ByteOrderMark byteOrderMark;

    public CarePreventionCsv(MultipartFile file){
        this.items = new ArrayList<>();

        confirmEncode(file);
        confirmBom(file);

        try {
            InputStream inputStream = file.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "shift-JIS"));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                items.add(CarePreventionCsvItem.CreateFromCsvLine(line));
            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void confirmBom(MultipartFile file) {
        this.byteOrderMark = ByteOrderMark.check(file);
        hasBom = !this.byteOrderMark.equals(ByteOrderMark.NOT_BE);
    }

    private void confirmEncode(MultipartFile file) {
        UniversalDetector detector = new UniversalDetector(null);

        try {
            byte[] buffer = new byte[4096];

            InputStream inputStream = file.getInputStream();

            int nread;

            while ((nread = inputStream.read(buffer)) > 0 && !detector.isDone()) {
                detector.handleData(buffer, 0, nread);
            }
            detector.dataEnd();

            this.encode = detector.getDetectedCharset();

        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public List<CarePreventionCsvItem> getItems() {
        return items;
    }

    public String getEncode() {
        return encode;
    }

    public boolean isHasBom() {
        return hasBom;
    }
}
