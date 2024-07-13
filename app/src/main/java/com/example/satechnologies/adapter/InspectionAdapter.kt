package com.example.satechnologies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.satechnologies.databinding.ItemInspectionCategoryBinding
import com.example.satechnologies.databinding.ItemQuestionBinding
import com.example.satechnologies.model.Category
import com.example.satechnologies.model.Question

class InspectionAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<InspectionAdapter.CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemInspectionCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category)
    }

    override fun getItemCount() = categories.size

    class CategoryViewHolder(private val binding: ItemInspectionCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.textViewCategoryName.text = "Category : " + category.name
            binding.recyclerViewQuestions.layoutManager = LinearLayoutManager(binding.root.context)
            binding.recyclerViewQuestions.adapter = QuestionAdapter(category.questions)
        }
    }
}

class QuestionAdapter(private val questions: List<Question>) :
    RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding =
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount() = questions.size

    class QuestionViewHolder(private val binding: ItemQuestionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            binding.textViewQuestionName.text = question.name
            binding.radioGroupAnswers.removeAllViews()
            question.answerChoices.forEach { answerChoice ->
                val radioButton = RadioButton(binding.root.context).apply {
                    text = answerChoice.name
                    id = answerChoice.id
                }
                binding.radioGroupAnswers.addView(radioButton)
                if (question.selectedAnswerChoiceId == answerChoice.id) {
                    binding.radioGroupAnswers.check(answerChoice.id)
                }
            }
            binding.radioGroupAnswers.setOnCheckedChangeListener { group, checkedId ->
                question.selectedAnswerChoiceId = checkedId
            }
        }
    }
}