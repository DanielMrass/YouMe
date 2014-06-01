package com.example.CallbackInterfaces;

import com.example.Models.PostQuestion;

public interface MyPostQuestionsCallback {
	void deletePostQuestion(int position);
	void addPostQuestion(PostQuestion pq);
}
