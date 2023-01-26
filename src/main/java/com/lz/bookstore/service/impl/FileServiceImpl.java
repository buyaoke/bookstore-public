package com.lz.bookstore.service.impl;

import com.lz.bookstore.entity.Files;
import com.lz.bookstore.mapper.FileMapper;
import com.lz.bookstore.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author buyaoke_lz
 * @since 2023-01-24
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, Files> implements IFileService {

}
