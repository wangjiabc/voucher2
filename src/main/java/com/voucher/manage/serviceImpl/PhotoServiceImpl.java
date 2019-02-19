package com.voucher.manage.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.voucher.manage.mapper.PhotoMapper;
import com.voucher.manage.model.Photo;
import com.voucher.manage.service.PhotoService;

@Service("photoService")
public class PhotoServiceImpl implements PhotoService{

    private PhotoMapper photoMapper;

    @Autowired
    public void setPhotoMapper(PhotoMapper photoMapper) {
        this.photoMapper = photoMapper;
    }

    @Override
    public int insertPhtoByOpenId(Photo photo) {
        // TODO Auto-generated method stub
        return photoMapper.insertPhtoByOpenId(photo);
    }

    @Override
    public List<Photo> getAllPhoto() {
        // TODO Auto-generated method stub
        return photoMapper.getAllPhoto();
    }

}
