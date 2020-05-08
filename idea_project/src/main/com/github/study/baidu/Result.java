package com.github.study.baidu;

import java.util.List;

/**
 * @Auther: shen
 * @Date: 2020/5/8 14:31
 * @Description:
 */
public class Result {

    String log_id;

    List<Word> words_result;

    Integer words_result_num;


    public Integer getWords_result_num() {
        return words_result_num;
    }

    public void setWords_result_num(Integer words_result_num) {
        this.words_result_num = words_result_num;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public List<Word> getWords_result() {
        return words_result;
    }

    public void setWords_result(List<Word> words_result) {
        this.words_result = words_result;
    }
}
