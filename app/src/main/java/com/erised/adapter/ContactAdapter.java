/*
 * Copyright (C) 2014 Francesco Azzola
 *  Surviving with Android (http://www.survivingwithandroid.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.erised.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.erised.R;
import com.erised.helper.RecyclerViewClick;
import com.erised.models.ContactInfo;

import java.util.List;

/**
 * Created by rahul on 21/9/15.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<ContactInfo> contactList;

    RecyclerViewClick event;

    public ContactAdapter(List<ContactInfo> contactList) {
        this.contactList = contactList;
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    @Override
    public void onBindViewHolder(ContactViewHolder contactViewHolder, final int position) {
        ContactInfo ci = contactList.get(position);
        contactViewHolder.vName.setText(ci.name);
        contactViewHolder.vSurname.setText(ci.surname);
//        contactViewHolder.vEmail.setText(ci.email);
//        contactViewHolder.vTitle.setText(ci.name + " " + ci.surname);

        contactViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (event != null) {

                    event.onItemClick(position);
                }
            }
        });
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_view_layout, viewGroup, false);

        return new ContactViewHolder(itemView);
    }

    public void setOnClick(RecyclerViewClick event) {

        this.event = event;
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder {

        protected TextView vName;
        protected TextView vSurname;
        protected TextView vEmail;
        protected TextView vTitle;

        public ContactViewHolder(View v) {
            super(v);
            vName = (TextView) v.findViewById(R.id.txtName);
            vSurname = (TextView) v.findViewById(R.id.txtSurname);
//            vEmail = (TextView) v.findViewById(R.id.txtEmail);
//            vTitle = (TextView) v.findViewById(R.id.title);
        }
    }
}