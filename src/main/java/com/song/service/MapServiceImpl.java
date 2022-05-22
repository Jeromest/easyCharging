package com.song.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.song.entity.Map;
import com.song.mapper.MapMapper;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, Map> implements MapService {
}
