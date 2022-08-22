package myweatherApplication.example.MyWeatherApplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import myweatherApplication.example.MyWeatherApplication.model.ForecastDaily;

import com.example.myWeatherApplication.R;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.VHDaily> {

    Context context;
    List<ForecastDaily> forecastDailyList;
    OnClickItemDay onClickItemDay;
    public DailyAdapter(Context context,List<ForecastDaily> forecastDailyList,OnClickItemDay onClickItemDay){
        this.context=context;
        this.forecastDailyList=forecastDailyList;
        this.onClickItemDay=onClickItemDay;

    }



    @NonNull
    @Override
    public VHDaily onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      LayoutInflater layoutInflater =LayoutInflater.from(context);
      View view ;
        view = layoutInflater.inflate(R.layout.items_day,parent,false);

        return new VHDaily(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull VHDaily holder, int position) {
      ForecastDaily itemForecast = forecastDailyList.get(position);
        holder.txt_maxTemp.setText(itemForecast.getTemp_max()+"°");
        holder.txt_minTemp.setText(itemForecast.getTemp_min()+"°");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("EEEE");
        try {
            Date dt = sdf.parse(itemForecast.getDate());
            String output = sdf1.format(dt);
            holder.txt_days.setText(output);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Picasso.get().load("http:".concat(itemForecast.getIcon())).into(holder.img_daily);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemDay.OnClick(itemForecast.getDate(),itemForecast.getTemp_max(),itemForecast.getIcon(),itemForecast.getCity());
            }
        });
    }

    @Override
    public int getItemCount() {
        return forecastDailyList.size();
    }

    class VHDaily extends RecyclerView.ViewHolder {
        TextView txt_days,txt_minTemp,txt_maxTemp,txt_description;
        ImageView img_daily;

        public VHDaily(@NonNull View itemView) {
            super(itemView);
            txt_days=itemView.findViewById(R.id.txt_days);
            txt_description=itemView.findViewById(R.id.txt_description);
            txt_minTemp=itemView.findViewById(R.id.txt_minTemp);
            txt_maxTemp=itemView.findViewById(R.id.txt_maxTemp);
            img_daily=itemView.findViewById(R.id.img_daily);


        }
    }

     public interface OnClickItemDay{
        void OnClick(String date,String temp,String icon,String city);
    }

}
