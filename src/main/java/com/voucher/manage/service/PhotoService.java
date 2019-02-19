package com.voucher.manage.service;

import java.util.List;

import com.voucher.manage.model.Photo;

public interface PhotoService {
    int insertPhtoByOpenId(Photo photo);

    List<Photo> getAllPhoto();
}
