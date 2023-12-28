/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dak.vam_transaction_management.seeder;

import com.dak.vam_transaction_management.models.va.MKecamatan;
import com.dak.vam_transaction_management.models.va.MKelurahan;
import com.dak.vam_transaction_management.repositories.va.MKecamatanRepository;
import com.dak.vam_transaction_management.repositories.va.MKelurahanRepository;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rohesa
 */

@Service
public class {{filename}} {
    private static final Logger logger = LogManager.getLogger({{filename}}.class);
    
    @Autowired
    MKecamatanRepository mKecamatanRepository;
    
    @Autowired
    MKelurahanRepository mKelurahanRepository;
    
    @Transactional
    public void execute() {
        try {
{{queryGoesHere}}
            logger.info("Seeder MKelurahan is Done");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error line: ", e.getStackTrace()[0].getLineNumber());
            logger.error("Error: ", e.getMessage());
        }
    }
    
    private void cr(String kodeKecamatan, String kode, String kodePos , String label, String status) {
        Optional<MKecamatan> mKecamatanOpt = mKecamatanRepository.findByKode(kodeKecamatan);
        if (mKecamatanOpt.isPresent()) {
            if (!mKelurahanRepository.existsByKode(kode)) {
                MKelurahan mKelurahan = new MKelurahan();
                mKelurahan.setKode(kode);
                mKelurahan.setKodePos(kodePos);
                mKelurahan.setMKecamatan(mKecamatanOpt.get());
                mKelurahan.setLabel(label);
                mKelurahan.setStatus(status);
                mKelurahanRepository.save(mKelurahan);
            }
        }
    }
}