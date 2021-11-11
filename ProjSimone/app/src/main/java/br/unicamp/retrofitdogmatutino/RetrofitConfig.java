package br.unicamp.retrofitdogmatutino;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class RetrofitConfig   {

      private static final String BASE_URL = "http://192.168.56.1:3000/api/dog/get/";

      private static Retrofit retrofit;

      public static Retrofit getRetrofitInstance(){
         if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
         }
         return retrofit;
      }
}
