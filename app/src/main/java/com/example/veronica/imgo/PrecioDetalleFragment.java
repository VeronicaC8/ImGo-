package com.example.veronica.imgo;


import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PrecioDetalleFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mIdSitio;

    private CollapsingToolbarLayout mCollapsingView;
    private ImageView mAvatar;
    private TextView mNombre;
    private TextView mDescripcion;
    private TextView mPrecioMax;
    private TextView mPrecioMin;

    private ControlBD helper;


    public PrecioDetalleFragment() {
        // Required empty public constructor
    }

    public static PrecioDetalleFragment newInstance(String idSitio) {
        PrecioDetalleFragment fragment = new PrecioDetalleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, idSitio);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mIdSitio = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root=inflater.inflate(R.layout.fragment_precio_detalle, container, false);
        mCollapsingView=(CollapsingToolbarLayout)getActivity().findViewById(R.id.toolbar_layout);
        mAvatar=(ImageView) getActivity().findViewById(R.id.iv_avatar);
        mNombre=(TextView)  root.findViewById(R.id.tv_descripcion);
        mPrecioMax=(TextView) root.findViewById(R.id.tv_precioMax);
        mPrecioMin=(TextView) root.findViewById(R.id.tv_precioMin);

        helper = new ControlBD(getActivity());

        return root;
    }

    //Si se elimina o modifica en sitio se utilizara para actualizar base
 //   @Override
    public void onActivityResult(int requestCode, int resultCode, Integer data){

    }

}
