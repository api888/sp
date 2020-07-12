package chao.android.tools.service_pools.path;

import chao.android.tools.service_pools.Printer;
import chao.java.tools.servicepool.IService;
import chao.java.tools.servicepool.annotation.Service;

/**
 * @author luqin
 * @since 2019-09-18
 */
@Service(path = "/app/path", scope = IService.Scope.global)
public class PathService implements Printer, IService {

    public void print(){
        System.out.println("this is a path service.");
    }
}
