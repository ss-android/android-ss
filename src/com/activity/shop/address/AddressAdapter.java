package com.activity.shop.address;

import java.util.List;

import com.activity.CommonActivity;
import com.lekoko.sansheng.R;
import com.sansheng.model.Address;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

/**
 * @author retryu E-mail:ruanchenyugood@gmail.com
 * @version create time：2013-9-8 上午10:53:10 declare:
 */
public class AddressAdapter extends BaseAdapter {
	private CommonActivity commonActivity;
	private LayoutInflater inflater;

	List<Address> addresses;

	public AddressAdapter(CommonActivity context) {
		commonActivity = context;
		inflater = context.getLayoutInflater();
	}

	@Override
	public int getCount() {
		if (addresses == null) {
			return 0;
		} else {
			return addresses.size();
		}
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = (View) inflater.inflate(R.layout.layout_address_item,
					null);

		}
		return convertView;

	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
