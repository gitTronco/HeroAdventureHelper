package com.troncodroide.heroadventurehelper.filter.interactor;

import android.os.AsyncTask;

import com.troncodroide.heroadventurehelper.Base.interfaces.ErrorListener;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter;
import com.troncodroide.heroadventurehelper.filter.presenter.FilterPresenter.FilterCategory;
import com.troncodroide.heroadventurehelper.models.CiticenData;
import com.troncodroide.heroadventurehelper.repository.Repository;
import com.troncodroide.heroadventurehelper.repository.api.TTL;
import com.troncodroide.heroadventurehelper.repository.interfaces.Response;
import com.troncodroide.heroadventurehelper.repository.models.CiticenDataRepository;
import com.troncodroide.heroadventurehelper.repository.request.BaseRequest;
import com.troncodroide.heroadventurehelper.repository.request.GetCiticensRequest;
import com.troncodroide.heroadventurehelper.repository.responses.GetCiticensResponse;

import java.util.LinkedList;
import java.util.List;

public class FilterInteractor {

    public interface FilterListener extends ErrorListener {
        void onGetFiltersSuccess(List<FilterCategory> items);
    }

    public void getFiltersCiticens(String town, final FilterListener listener) {
        Repository.TOWN.getCiticens(new BaseRequest<>(new GetCiticensRequest(town)), new Response.Listener<GetCiticensResponse>() {
            @Override
            public void onSuccess(final GetCiticensResponse data, boolean hideLoading) {


                new AsyncTask<Void, Void, Void>() {
                    List<FilterCategory> filters;

                    @Override
                    protected Void doInBackground(Void... params) {
                        filters = validateAndTrasnformCiticenData(data.getData());
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void voidd) {
                        super.onPostExecute(voidd);
                        listener.onGetFiltersSuccess(filters);

                    }
                }.execute();

            }

            @Override
            public void onError(Response.Error data) {
                listener.onError(data.getErrorCode(), data.toString());
            }

            private List<FilterCategory> validateAndTrasnformCiticenData(List<CiticenDataRepository> list) {

                FilterCategory hairCategory = new FilterCategory(FilterCategory.TYPE_VALUES, "HAIR");
                FilterCategory ageCategory = new FilterCategory(FilterCategory.TYPE_RANGED, "AGE");
                FilterCategory weightCategory = new FilterCategory(FilterCategory.TYPE_RANGED, "WEIGHT");
                FilterCategory heightCategory = new FilterCategory(FilterCategory.TYPE_RANGED, "HEIGHT");
                FilterCategory profesionsCategory = new FilterCategory(FilterCategory.TYPE_VALUES, "PROFESIONS");
                for (CiticenDataRepository data : list) {
                    hairCategory.addValue(new FilterPresenter.FilterValue(data.getHair_color()));
                    ageCategory.addValue(new FilterPresenter.FilterValue("" + data.getAge()));
                    weightCategory.addValue(new FilterPresenter.FilterValue("" + data.getWeight()));
                    heightCategory.addValue(new FilterPresenter.FilterValue("" + data.getHeight()));
                    for (String profesion : data.getProfessions()) {
                        profesionsCategory.addValue(new FilterPresenter.FilterValue(profesion));
                    }
                }
                List<FilterCategory> listCategories = new LinkedList<FilterCategory>();
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