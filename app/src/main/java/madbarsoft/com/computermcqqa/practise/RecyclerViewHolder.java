package madbarsoft.com.computermcqqa.practise;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import at.blogc.android.views.ExpandableTextView;
import madbarsoft.com.computermcqqa.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder{
    private TextView answerListHolderTV;
    private TextView questionTitleTV;
    private TextView show_more;
    private ExpandableTextView correctAnsExpandableTV;

    public RecyclerViewHolder(final View v) {
        super(v);
        show_more = (TextView) v.findViewById(R.id.show_more);
        answerListHolderTV = (TextView) v.findViewById(R.id.answerListHolderTV);
        questionTitleTV = (TextView) v.findViewById(R.id.questionTitleTV);
        correctAnsExpandableTV = (ExpandableTextView) v.findViewById(R.id.correctAnsExpandableTV);
    }

    public TextView getShow_more() {
        return show_more;
    }

    public void setShow_more(TextView show_more) {
        this.show_more = show_more;
    }

    public ExpandableTextView getExpandableTextView() {
        return correctAnsExpandableTV;
    }

    public void setExpandableTextView(ExpandableTextView ansExpandableTV) {
        this.correctAnsExpandableTV = ansExpandableTV;
    }

    public TextView getMcqAnswerListHolderId() {
        return answerListHolderTV;
    }

    public void setMcqAnswerListHolderId(TextView answerListHolderTV) {
        this.answerListHolderTV = answerListHolderTV;
    }

    public TextView getQuestionTitleId() {
        return questionTitleTV;
    }

    public void setQuestionTitleId(TextView questionTitleTV) {
        this.questionTitleTV = questionTitleTV;
    }
}

