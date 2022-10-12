package duan.server.service.impl;

import duan.server.entity.Term;
import duan.server.mapper.TermMapper;
import duan.server.service.ITermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author duanyhui
 * @since 2022-10-11
 */
@Service
public class TermServiceImpl extends ServiceImpl<TermMapper, Term> implements ITermService {

}
