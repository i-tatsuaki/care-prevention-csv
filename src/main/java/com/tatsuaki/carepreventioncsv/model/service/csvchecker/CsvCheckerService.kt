package com.tatsuaki.carepreventioncsv.model.service.csvchecker

import com.tatsuaki.carepreventioncsv.model.domain.CarePreventionCsv
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CsvCheckerService {

    fun check(file: MultipartFile): CarePreventionCsv {
        return CarePreventionCsv(file)
    }
}
