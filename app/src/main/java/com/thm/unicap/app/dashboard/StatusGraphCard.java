package com.thm.unicap.app.dashboard;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.echo.holographlibrary.Utils;
import com.thm.unicap.app.R;
import com.thm.unicap.app.UnicapApplication;
import com.thm.unicap.app.model.Student;
import com.thm.unicap.app.model.Subject;
import com.thm.unicap.app.model.SubjectStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.gmariotti.cardslib.library.internal.Card;

public class StatusGraphCard extends Card implements PieGraph.OnSliceClickedListener {

    private PieGraph mPieGraph;
    private TextView mStatusPercentage;
    private int mActiveSlice = 0;

    public StatusGraphCard(Context context) {
        super(context, R.layout.card_status_graph);
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        Student student = UnicapApplication.getStudent();

        List<Subject> subjects = student.getActualSubjects();

        float approvedCount = 0f;
        float repprovedCount = 0f;
        float waitingCount = 0f;

        float totalCount = 0f;

        for (Subject subject : subjects) {
            SubjectStatus actualSubjectStatus = subject.getActualSubjectStatus();

            switch (actualSubjectStatus.getFlowSituation()) {
                case APPROVED:
                    approvedCount++;
                    break;
                case REPROVED:
                    repprovedCount++;
                    break;
                case WAITING:
                case WAITING_FINAL:
                    waitingCount++;
                    break;
            }
        }

        totalCount = approvedCount + repprovedCount + waitingCount;

        if(approvedCount >= repprovedCount && approvedCount >= waitingCount)
            mActiveSlice = 0;
        else if(repprovedCount >= approvedCount && repprovedCount >= waitingCount)
            mActiveSlice = 1;
        else if(waitingCount >= approvedCount && waitingCount >= repprovedCount)
            mActiveSlice = 2;

        mPieGraph = (PieGraph)parent.findViewById(R.id.graph);
        mStatusPercentage = (TextView) parent.findViewById(R.id.status_percentage);
        PieSlice slice;

        slice = new PieSlice();
        slice.setColor(mContext.getResources().getColor(android.R.color.holo_green_light));
        slice.setSelectedColor(Utils.darkenColor(mContext.getResources().getColor(android.R.color.holo_green_light)));
        slice.setTitle(mContext.getString(R.string.approved));
        slice.setValue((approvedCount / totalCount) * 100f);
        mPieGraph.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(mContext.getResources().getColor(android.R.color.holo_red_light));
        slice.setSelectedColor(Utils.darkenColor(mContext.getResources().getColor(android.R.color.holo_red_light)));
        slice.setTitle(mContext.getString(R.string.repproved));
        slice.setValue((repprovedCount / totalCount) * 100f);
        mPieGraph.addSlice(slice);

        slice = new PieSlice();
        slice.setColor(mContext.getResources().getColor(R.color.unicap_light_gray));
        slice.setSelectedColor(Utils.darkenColor(mContext.getResources().getColor(R.color.unicap_light_gray)));
        slice.setTitle(mContext.getString(R.string.waiting));
        slice.setValue((waitingCount / totalCount) * 100f);
        mPieGraph.addSlice(slice);

        mPieGraph.setOnSliceClickedListener(this);
        mPieGraph.setInnerCircleRatio(100);

        //Workaround to fix PieGraph bug
        mPieGraph.setPadding(1);

        onClick(mActiveSlice);

        Animation rotation = AnimationUtils.loadAnimation(mContext, R.anim.clockwise_rotation);
        mPieGraph.startAnimation(rotation);
    }

    @Override
    public void onClick(int index) {
        mActiveSlice = index;
        PieSlice slice = mPieGraph.getSlice(index);

        mStatusPercentage.setText(String.valueOf((int)slice.getValue())+"%");
        mStatusPercentage.setTextColor(slice.getColor());
    }

    public int getActiveSlice() {
        return mActiveSlice;
    }

}
