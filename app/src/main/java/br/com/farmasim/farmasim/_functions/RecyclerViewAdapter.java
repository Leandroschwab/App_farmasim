package br.com.farmasim.farmasim._functions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import java.util.ArrayList;

import br.com.farmasim.farmasim.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private ArrayList<String> mNomeRemedio = new ArrayList<>();
    private ArrayList<String> mHoraRemedio = new ArrayList<>();
    private ArrayList<Integer> mBola = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter( Context mContext, ArrayList<String> mNomeRemedio, ArrayList<String> mHoraRemedio, ArrayList<Integer> mBola) {
        this.mNomeRemedio = mNomeRemedio;
        this.mHoraRemedio = mHoraRemedio;
        this.mBola = mBola;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleviewremedios, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d("myTag","onBindViewHolder: called.");
        holder.nomeRemedio.setText(mNomeRemedio.get(position));
        Log.d("myTag","onBindViewHolder: nomeRemedio.");
        holder.horaRemedio.setText(mHoraRemedio.get(position));
        Log.d("myTag","onBindViewHolder: horaRemedio.");
        holder.bola.setCardBackgroundColor(mBola.get(position));
        Log.d("myTag","onBindViewHolder: finished.");
    }

    @Override
    public int getItemCount() {
        return mNomeRemedio.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nomeRemedio;
        TextView horaRemedio;
        CardView bola;
        RelativeLayout ParentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            nomeRemedio = (TextView) itemView.findViewById(R.id.nomeRemedio);
            horaRemedio = (TextView) itemView.findViewById(R.id.horaRemedio);
            bola = (CardView) itemView.findViewById(R.id.bola);
            ParentLayout = (RelativeLayout) itemView.findViewById(R.id.parent_layout);
        }
    }
}
