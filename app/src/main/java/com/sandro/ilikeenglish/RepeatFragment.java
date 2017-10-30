package com.sandro.ilikeenglish;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;



public class RepeatFragment extends Fragment {


    public RepeatFragment() {
        // Required empty public constructor
    }

    final Random random = new Random();
    String fieldForQuestions, fieldForTrueAnswer, fieldForRandomAnswer;
    TextView question, optionOne, optionTwo, trueInfo;
    MainActivity massive = new MainActivity();
    int  fieldForID, fieldForChecking, randomFalseAnswer;
    boolean randomQuestion;
    int countOfQuestions = 6;

    void setAnswers(int forNextQuestion){
        fieldForID = Integer.parseInt(massive.massiv[forNextQuestion][0]);
        fieldForQuestions = String.valueOf(massive.massiv[forNextQuestion][1]);
        fieldForTrueAnswer = String.valueOf(massive.massiv[forNextQuestion][2]);
        fieldForChecking = Integer.parseInt(massive.massiv[forNextQuestion][3]);
        randomFalseAnswer = random.nextInt(countOfQuestions);

        while (randomFalseAnswer == forNextQuestion){
            randomFalseAnswer = random.nextInt(countOfQuestions);
        }
        fieldForRandomAnswer = String.valueOf(massive.massiv[randomFalseAnswer][2]);

        randomQuestion = random.nextBoolean();

        question.setText(fieldForQuestions);

        if (randomQuestion == true){
            optionOne.setText(fieldForTrueAnswer);
            optionTwo.setText(fieldForRandomAnswer);

        } else {
            optionOne.setText(fieldForRandomAnswer);
            optionTwo.setText(fieldForTrueAnswer);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repeat, container, false);

        question = (TextView) view.findViewById(R.id.question);
        optionOne = (TextView) view.findViewById(R.id.answerOne);
        optionTwo = (TextView) view.findViewById(R.id.answerTwo);

        setAnswers(random.nextInt(countOfQuestions));
        question.setText(fieldForQuestions);


        optionOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trueAnswer = optionOne.getText().toString();
            if(trueAnswer == fieldForTrueAnswer){
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Правильно", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                        "Неправильно", Toast.LENGTH_SHORT);
                toast.show();
            }
                setAnswers(random.nextInt(countOfQuestions));
                question.setText(fieldForQuestions);
            }
        });

        optionTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String trueAnswer = optionTwo.getText().toString();
                if(trueAnswer == fieldForTrueAnswer){
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Правильно", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                            "Неправильно", Toast.LENGTH_SHORT);
                    toast.show();
                }
                setAnswers(random.nextInt(countOfQuestions));
                question.setText(fieldForQuestions);
            }
        });

     return view;
    }


}
