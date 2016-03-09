package com.troncodroide.heroadventurehelper.filter.interactor;

import com.troncodroide.heroadventurehelper.Base.interfaces.ErrorListener;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.Repository;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;
import com.troncodroide.heroadventurehelper.repository.responses.GetCiticensResponse;

import java.util.LinkedList;
import java.util.List;

public class FilterInteractor {

    public interface FilterListener extends ErrorListener {
        void onGetFiltersSuccess(List<FilterPresenter.FilterCategory> items);
    }

    public void getFiltersCiticens(String town, final FilterListener listener) {
        Repository.TOWN.getCiticens(new BaseRequest<>(new GetCiticensRequest(town)), new Response.Listener<GetCiticensResponse>() {
            @Override
            public void onSuccess(GetCiticensResponse data, boolean hideLoading) {
                listener.onGetFiltersSuccess(validateAndTrasnformCiticenData(data.getData()));
            }

            @Override
            public void onError(Response.Error data) {
                listener.onError(data.getErrorCode(), data.toString());
            }

            private List<FilterPresenter.FilterCategory> validateAndTrasnformCiticenData(List<CiticenDataRepository> list) {
                List<CiticenData> toRet = new LinkedList<CiticenData>();
                FilterPresenter.FilterCategory hairCategory = new FilterPresenter.FilterCategory();
                FilterPresenter.FilterCategory ageCategory = new FilterPresenter.FilterCategory();
                FilterPresenter.FilterCategory weightCategory = new FilterPresenter.FilterCategory();
                FilterPresenter.FilterCategory heightCategory = new FilterPresenter.FilterCategory();
                FilterPresenter.FilterCategory profesionsCategory = new FilterPresenter.FilterCategory();
                for (CiticenDataRepository data : list) {
                    hairCategory.addValue(new FilterPresenter.FilterValue(data.getHair_color()));
                    ageCategory.addValue(new FilterPresenter.FilterValue("" + data.getAge()));
                    weightCategory.addValue(new FilterPresenter.FilterValue("" + data.getWeight()));
                    heightCategory.addValue(new FilterPresenter.FilterValue("" + data.getHeight()));
                    for (String profesion : data.getProfessions()) {
                        profesionsCategory.addValue(new FilterPresenter.FilterValue(profesion));
                    }

                }
                List<FilterPresenter.FilterCategory> listCategories = new LinkedList<FilterPresenter.FilterCategory>();
                listCategories.add(hairCategory);
                listCategories.add(ageCategory);
                listCategories.add(weightCategory);
                listCategories.add(heightCategory);
                listCategories.add(profesionsCategory);
                return listCategories;
            }
        });
    }
}

