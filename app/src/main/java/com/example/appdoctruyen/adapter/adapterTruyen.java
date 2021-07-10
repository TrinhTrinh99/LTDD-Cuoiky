package com.example.appdoctruyen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.appdoctruyen.R;
import com.example.appdoctruyen.model.Truyen;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class adapterTruyen extends BaseAdapter {
    private Context context;
    private ArrayList<Truyen> listTruyen;

    public adapterTruyen(Context context, ArrayList<Truyen> listTruyen) {
        this.context = context;
        this.listTruyen = listTruyen;
    }

    @Override
    public int getCount() {
        return listTruyen.size();
    }

    @Override
    public Object getItem(int position) {
        return listTruyen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void filterList(ArrayList<Truyen> filteredList) {
        listTruyen = filteredList;
        notifyDataSetChanged();

    }

    public class ViewHolder{
        TextView  txttenTruyen;
        ImageView imgTruyen;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        viewHolder = new ViewHolder();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.newtruyen,null);
        viewHolder.txttenTruyen = convertView.findViewById(R.id.tvtentr);

        viewHolder.imgTruyen =convertView.findViewById(R.id.imvnewtr);
        convertView.setTag(viewHolder);
        Truyen truyen = (Truyen)getItem(position);
        viewHolder.txttenTruyen.setText(truyen.getTenTruyen());

        Picasso.get().load(truyen.getHinhAnh()).placeholder(R.drawable.ic_download).error(R.drawable.ic_image_24).into(viewHolder.imgTruyen);

        return convertView;
    }
}
