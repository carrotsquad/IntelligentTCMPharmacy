package com.zhangqianyuan.teamwork.intelligenttcmpharmacy.contract;

import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.bean.DrugSearchBean;
import com.zhangqianyuan.teamwork.intelligenttcmpharmacy.view.interfaces.BaseView;

import io.reactivex.Observer;

/**
 * @Description: 药材搜索Contract
 * Created at: 2019/2/26 16:56
 * @author: zhangqianyuan
 * @Email: zhang.qianyuan@foxmail.com
 * @version:
 * @updateAuthor:
 * @updateDes:
 */
public interface SearchContract {

    @FunctionalInterface
    interface SearchView extends BaseView{
        void acquireSearchResult(DrugSearchBean searchBean);
    }

    @FunctionalInterface
    interface SearchPresenter{
        void getSearchResult();
    }

    @FunctionalInterface
    interface SearchModel{
        void getSearchResult(Observer<DrugSearchBean> observer);
    }
}
