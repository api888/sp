package chao.java.tools.servicepool;

import java.util.List;

/**
 * @author qinchao
 * @since 2019/4/30
 */
public class DefaultInitService extends DefaultService implements IInitService {

    @Override
    public void onInit() {

    }

    @Override
    public boolean async() {
        return false;
    }

    @Override
    public List<IInitService> dependencies() {
        return null;
    }

}
