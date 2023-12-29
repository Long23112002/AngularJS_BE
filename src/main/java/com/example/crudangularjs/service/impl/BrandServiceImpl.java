package com.example.crudangularjs.service.impl;

import com.example.crudangularjs.entity.Brand;
import com.example.crudangularjs.repository.BrandRepository;
import com.example.crudangularjs.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;
    @Autowired
    public void setBrandRepository(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> getAllBrandName() {
        List<Brand> listBrand = brandRepository.findAll();
        return listBrand;
    }
}
