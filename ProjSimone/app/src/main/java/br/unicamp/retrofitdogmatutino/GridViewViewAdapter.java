package br.unicamp.retrofitdogmatutino;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import java.util.List;

class GridViewViewAdapter extends BaseAdapter {

    private List<Dog> listaDog;
    private Context context;
    //private CircularArray<Object> Picasso;

    public GridViewViewAdapter(Context context, List<Dog> recebeParametroListaDog){
        this.listaDog = recebeParametroListaDog;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listaDog.size();
    }

    @Override
    public Object getItem(int position) {
        return listaDog.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_gridview, parent,false);
        }

        TextView tvNome = view.findViewById(R.id.tvNome);
        TextView tvRaca = view.findViewById(R.id.tvRaca);
        ImageView dogImageView = view.findViewById(R.id.dogImageView);

        Dog dog = listaDog.get(position);

        tvNome.setText(dog.getNome());
        tvRaca.setText(dog.getRaca());

        if((dog.getImagem() != null) && (dog.getImagem().length()>0)){
            Picasso.get().load(dog.getImagem()).into(dogImageView);
        }else{
            Toast.makeText(context, "Não carregou a imagem", Toast.LENGTH_LONG).show();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, dog.getNome(), Toast.LENGTH_LONG).show();

                Dog objDog = new Dog(dog.getId(), dog.getNome(), dog.getRaca(), dog.getImagem());

                Intent intent = new Intent(context,CadastroPetActivity.class);
                intent.putExtra("dogSerializable",(Serializable) objDog);
                context.startActivity(intent);

            }
        });
        return view;
    }
}
