package com.blackcard.logan.util.base;

import android.content.Context;
import android.support.v4.app.Fragment;

/**
 * Created by Logan on 2019/3/27.
 */
public abstract class BaseMvpFragment<M extends Model, V extends View, P extends BasePresenter> extends Fragment implements BaseMvp<M, V, P> {
    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter = createPresenter();
        if (presenter != null) {
            presenter.registerModel(createModel());
            presenter.registerView(createView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}