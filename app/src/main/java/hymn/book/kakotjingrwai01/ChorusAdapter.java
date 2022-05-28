package hymn.book.kakotjingrwai01;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

public class ChorusAdapter extends RecyclerView.Adapter<ChorusAdapter.viewHolder>{

    private ArrayList<ChorusModel> chorusModelArrayList;
    Context context;

    public ChorusAdapter(ArrayList<ChorusModel> chorusModelArrayList, Context context) {
        this.chorusModelArrayList = chorusModelArrayList;
        this.context = context;
    }


    @NonNull
    @Override
    public ChorusAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.chorus_itemlist,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChorusAdapter.viewHolder holder, int position) {
        holder.chorusid.setText(chorusModelArrayList.get(position).getId());

        holder.chorusHymncard.setOnClickListener(V->{
            Intent intent=new Intent(context,DisplayChorus.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("ID",chorusModelArrayList.get(position).getId());
            intent.putExtra("LYRIC",chorusModelArrayList.get(position).getLyric());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return chorusModelArrayList.size();
    }

    public class viewHolder  extends RecyclerView.ViewHolder{

        private TextView chorusid;
        private MaterialCardView chorusHymncard;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            chorusid=itemView.findViewById(R.id.chorusNumberitem);
            chorusHymncard=itemView.findViewById(R.id.chorusHymncard);

        }
    }
}

