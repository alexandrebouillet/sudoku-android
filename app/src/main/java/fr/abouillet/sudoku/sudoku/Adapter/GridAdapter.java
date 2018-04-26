package fr.abouillet.sudoku.sudoku.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.abouillet.sudoku.sudoku.R;
import fr.abouillet.sudoku.sudoku.model.Grid;

public class GridAdapter extends ArrayAdapter<Grid> {


    private Context mContext;
    private List<Grid> gridsList = new ArrayList<>();

    public GridAdapter(@NonNull Context context, ArrayList<Grid> list) {
        super(context, 0 , list);
        mContext = context;
        gridsList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.list_item,parent,false);

        Grid currentGrid = gridsList.get(position);

        TextView name = (TextView) listItem.findViewById(R.id.textView_name);
        name.setText(currentGrid.getName());

        TextView pourcent= (TextView) listItem.findViewById(R.id.textView_pourcent);
        pourcent.setText(String.valueOf(currentGrid.getCompletePourcent()));
        if(currentGrid.getCompletePourcent() > 40){
            pourcent.setTextColor(Color.GREEN);
        }else{
            pourcent.setTextColor(Color.RED);
        }

        return listItem;
    }

}
