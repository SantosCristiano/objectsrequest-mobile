package br.com.diebold.partsrequest.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.card.MaterialCardView;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.controller.PedidoController;
import br.com.diebold.partsrequest.data.api.response.UsuarioResponse;
import br.com.diebold.partsrequest.databinding.FragmentHomeBinding;
import br.com.diebold.partsrequest.modelView.PedidoView;
import br.com.diebold.partsrequest.ui.status.AbertosActivity;
import br.com.diebold.partsrequest.ui.status.CanceladosActivity;
import br.com.diebold.partsrequest.ui.status.EnviadosActivity;
import br.com.diebold.partsrequest.ui.status.ErrosActivity;
import br.com.diebold.partsrequest.ui.status.FinalizadosActivity;
import br.com.diebold.partsrequest.ui.status.PendentesActivity;
import br.com.diebold.partsrequest.utils.PreferencesUserUtil;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private MaterialCardView cardAbertos;
    private MaterialCardView cardEnviados;
    private MaterialCardView cardFinalizados;
    private MaterialCardView cardCancelados;
    private MaterialCardView cardPendentes;
    private MaterialCardView cardErros;

    private TextView tvAbertos;
    private TextView tvEnviados;
    private TextView tvFinalizados;
    private TextView tvCancelados;
    private TextView tvPendentes;
    private TextView tvErros;

    private String qtdAbertos;
    private String qtdEnviados;
    private String qtdFinalizados;
    private String qtdCancelados;
    private String qtdPendentes;
    private String qtdErros;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.cardAbertos = binding.cardAbertos;
        this.cardEnviados = binding.cardEnviados;
        this.cardFinalizados = binding.cardFinalizados;
        this.cardCancelados = binding.cardCancelados;
        this.cardPendentes = binding.cardPendentes;
        this.cardErros = binding.cardErros;

        this.tvAbertos = binding.tvAbertos;
        this.tvEnviados = binding.tvEnviados;
        this.tvFinalizados = binding.tvFinalizados;
        this.tvCancelados = binding.tvCancelados;
        this.tvPendentes = binding.tvPendentes;
        this.tvErros = binding.tvErros;


        carregarTelaInicial();

        Bundle data = getArguments();

        if(data != null) {
            qtdAbertos = data.getString("qtdAbertos");
            qtdEnviados = data.getString("qtdEnviados");
            qtdFinalizados = data.getString("qtdFinalizados");
            qtdCancelados = data.getString("qtdCancelados");
            qtdPendentes = data.getString("qtdPendentes");
            qtdErros = data.getString("qtdErros");
        } else {
            qtdAbertos = "00";
            qtdEnviados = "00";
            qtdFinalizados = "00";
            qtdCancelados = "00";
            qtdPendentes = "00";
            qtdErros = "00";
        }

        tvAbertos.setText(qtdAbertos);
        tvEnviados.setText(qtdEnviados);
        tvFinalizados.setText(qtdFinalizados);
        tvCancelados.setText(qtdCancelados);
        tvPendentes.setText(qtdPendentes);
        tvErros.setText(qtdErros);

        this.cardAbertos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), AbertosActivity.class);
                startActivity(intent);
            }
        });

        this.cardEnviados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), EnviadosActivity.class);
                startActivity(intent);
            }
        });

        this.cardFinalizados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), FinalizadosActivity.class);
                startActivity(intent);
            }
        });

        this.cardCancelados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), CanceladosActivity.class);
                startActivity(intent);
            }
        });

        this.cardPendentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), PendentesActivity.class);
                startActivity(intent);
            }
        });

        this.cardErros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplication(), ErrosActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }


    private void carregarTelaInicial(){

        UsuarioResponse DadosUsuarioLogado = PreferencesUserUtil.getObjectFromPref(this.getContext(), UsuarioResponse.class,  PreferencesUserUtil.PREFS_USER_DATA_LOGGED);

        PedidoView pedidos = PedidoController.getInstance(this.getContext())
                .obterPedidosDB(DadosUsuarioLogado.getTecnico().getIdTecnico());

        int qtdAbertos = 0;
        int qtdEnviados = 0;
        int qtdFinalizados = 0;
        int qtdCancelados = 0;
        int qtdPendentes = 0;
        int qtdErros = 0;

        if(pedidos != null) {

            for(int i =0; i < pedidos.getPedidos().size() ; i++) {
                if(pedidos.getPedidos().get(i).getStPstatusStatus() != null) {
                    switch (pedidos.getPedidos().get(i).getStPstatusStatus()) {
                        case "Pendente":
                            qtdPendentes++;
                            break;
                        case "Aberto":
                            qtdAbertos++;
                            break;
                        case "Em Transporte":
                            qtdEnviados++;
                            break;
                        case "Finalizado":
                            qtdFinalizados++;
                            break;
                        case "Cancelado":
                            qtdCancelados++;
                            break;
                        case "Erro":
                            qtdErros++;
                            break;
                        default:
                            qtdAbertos++;
                            break;
                    }
                }
            }
        }

        HomeFragment homeFragment = new HomeFragment();

        Bundle data = new Bundle();

        if(qtdAbertos < 10) {
            data.putString("qtdAbertos", "0" + Integer.toString(qtdAbertos));
        } else {
            data.putString("qtdAbertos", Integer.toString(qtdAbertos));
        }

        if(qtdEnviados < 10) {
            data.putString("qtdEnviados", "0" + Integer.toString(qtdEnviados));
        } else {
            data.putString("qtdEnviados", Integer.toString(qtdEnviados));
        }

        if(qtdFinalizados < 10) {
            data.putString("qtdFinalizados", "0" + Integer.toString(qtdFinalizados));
        } else {
            data.putString("qtdFinalizados", Integer.toString(qtdFinalizados));
        }

        if(qtdCancelados < 10) {
            data.putString("qtdCancelados", "0" + Integer.toString(qtdCancelados));
        } else {
            data.putString("qtdCancelados", Integer.toString(qtdCancelados));
        }

        if(qtdPendentes < 10) {
            data.putString("qtdPendentes", "0" + Integer.toString(qtdPendentes));
        } else {
            data.putString("qtdPendentes", Integer.toString(qtdPendentes));
        }

        if(qtdErros < 10) {
            data.putString("qtdErros", "0" + Integer.toString(qtdErros));
        } else {
            data.putString("qtdErros", Integer.toString(qtdErros));
        }

        this.setArguments(data);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}