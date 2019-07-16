package chao.android.tools.servicepool;

import android.util.Log;
import java.io.File;
import java.util.Map;
import net.bytebuddy.android.AndroidClassLoadingStrategy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.dynamic.TypeResolutionStrategy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.dynamic.scaffold.TypeInitializer;

/**
 * @author qinchao
 * @since 2019/6/19
 */

public enum AndroidLazyStrategy implements TypeResolutionStrategy, TypeResolutionStrategy.Resolved {

    /**
     * The singleton instance.
     */
    INSTANCE;

    File NO_OP_DEX_DIR = new File(AndroidServicePool.getContext().getCacheDir(), "dexs");


    private AndroidClassLoadingStrategy loadingStrategy = null;

    /**
     * {@inheritDoc}
     */
    public Resolved resolve() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    public TypeInitializer injectedInto(TypeInitializer typeInitializer) {
        return typeInitializer;
    }

    /**
     * {@inheritDoc}
     */
    public <S extends ClassLoader> Map<TypeDescription, Class<?>> initialize(DynamicType dynamicType,
                                                                             S classLoader,
                                                                             ClassLoadingStrategy<? super S> classLoadingStrategy) {
        makeDexDir();
        if (loadingStrategy == null) {
            loadingStrategy = new AndroidClassLoadingStrategy.Injecting(NO_OP_DEX_DIR);
        }
        return loadingStrategy.load(classLoader, dynamicType.getAllTypes());
    }

    private void makeDexDir() {
        if (NO_OP_DEX_DIR.exists() && NO_OP_DEX_DIR.isFile()) {
            if (NO_OP_DEX_DIR.delete()) {
                Log.e("qinchao", "delete failed!!!");
            }
        }
        if (!NO_OP_DEX_DIR.exists()) {
            if (!NO_OP_DEX_DIR.mkdirs()) {
                Log.e("qinchao", "mkdir failed!!!");
            }
        }
    }
}