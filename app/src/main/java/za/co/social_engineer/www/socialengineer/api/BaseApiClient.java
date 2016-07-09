package za.co.social_engineer.www.socialengineer.api;

import android.content.Context;

import java.net.CookieManager;

/**
 * Created by Marcel Teixeira on 2016/07/10.
 */
public abstract class BaseApiClient<T> {

    private T server;
    private Context context;
    private Class<T> serverClass;
    private CookieManager mCookieManager;

    public BaseApiClient(Context context, Class<T> serverClass) {
        this.context = context;
        this.serverClass = serverClass;
    }
}
