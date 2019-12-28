package com.tatsuaki.carepreventioncsv.model.service.csvchecker;

import com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsv;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CsvCheckerService {

    public CarePreventionCsv check(MultipartFile file){
        return new CarePreventionCsv(file);
    }
}
