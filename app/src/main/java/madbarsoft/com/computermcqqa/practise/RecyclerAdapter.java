package madbarsoft.com.computermcqqa.practise;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;


import java.util.List;

import at.blogc.android.views.ExpandableTextView;
import madbarsoft.com.computermcqqa.R;
import madbarsoft.com.computermcqqa.main.AnswerModel;
import madbarsoft.com.computermcqqa.main.QuestionAnswerModel;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {
    List<QuestionAnswerModel> questionAnswerModelList;
    private LayoutInflater layoutInflater;
    private Context context;

    public RecyclerAdapter(Context context, List<QuestionAnswerModel> questionAnswerModelList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.questionAnswerModelList = questionAnswerModelList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = layoutInflater.inflate(R.layout.practise_item_recycler_view, parent, false);
        return new RecyclerViewHolder(item);
    }

    @Override
    public void onBindViewHolder(final RecyclerViewHolder holder, int position) {
        String mcqAnsList="";
        String correctAns="";
        for(AnswerModel obj:questionAnswerModelList.get(position).getMcqAnswerModelList()){
            mcqAnsList+= obj.getTitle().toString()+"\n";
            if(obj.getIsRightAns()==1){
                correctAns+= obj.getTitle();
            }
        }
        holder.getQuestionTitleId().setText(questionAnswerModelList.get(position).getTitle() );
        holder.getMcqAnswerListHolderId().setText(mcqAnsList);
        holder.getExpandableTextView().setText(correctAns);

        final ExpandableTextView expandableTextView =  holder.getExpandableTextView();

// set animation duration via code, but preferable in your layout files by using the animation_duration attribute
        expandableTextView.setAnimationDuration(750L);

        // set interpolators for both expanding and collapsing animations
        expandableTextView.setInterpolator(new OvershootInterpolator());

        holder.getShow_more().setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView.isExpanded())
                {
                    expandableTextView.collapse();
              //      holder.getShow_more().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_down, 0);
                }
                else
                {
                    expandableTextView.expand();
                 //   holder.getShow_more().setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.arrow_up, 0);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return questionAnswerModelList.size();
    }


}
