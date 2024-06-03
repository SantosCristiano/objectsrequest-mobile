package br.com.diebold.partsrequest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.com.diebold.partsrequest.R;
import br.com.diebold.partsrequest.modelView.EquipamentoBomItemView;


public class AutoCompleteBomAdapter extends ArrayAdapter<EquipamentoBomItemView> {
    private List<EquipamentoBomItemView> equipamentoBomListFull;

    public AutoCompleteBomAdapter(@NonNull Context context, @NonNull List<EquipamentoBomItemView> equipamentoBomList) {
        super(context, 0, equipamentoBomList);
        equipamentoBomListFull = new ArrayList<>(equipamentoBomList);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return equipamentoBomFilter;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.equipamento_bom_autocomplete_row, parent, false
            );
        }

        TextView textViewName = convertView.findViewById(R.id.text_view_name);
        TextView textViewFamily = convertView.findViewById(R.id.text_view_family);
        TextView textViewDescription = convertView.findViewById(R.id.text_view_description);

        EquipamentoBomItemView equipamentoBomItem = getItem(position);

        if (equipamentoBomItem != null) {
            textViewName.setText(equipamentoBomItem.getItemCode());
            textViewFamily.setText(equipamentoBomItem.getItemFamily());
            textViewDescription.setText(equipamentoBomItem.getItemDescription());
        }

        return convertView;
    }

    private Filter equipamentoBomFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<EquipamentoBomItemView> suggestions = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                suggestions.addAll(equipamentoBomListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (EquipamentoBomItemView item : equipamentoBomListFull) {
                    if (item.getItemCode().toLowerCase().contains(filterPattern)) {
                        suggestions.add(item);
                    }
                }
            }

            results.values = suggestions;
            results.count = suggestions.size();

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            clear();
            addAll((List) results.values);
            notifyDataSetChanged();
        }

        @Override
        public CharSequence convertResultToString(Object resultValue) {
            return ((EquipamentoBomItemView) resultValue).getItemCode();
        }
    };
}
