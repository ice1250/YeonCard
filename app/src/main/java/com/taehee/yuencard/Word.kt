package com.taehee.yuencard

object Word {
    private val words =
        mutableListOf(
            "가위",
            "나비",
            "다리",
            "라면",
            "마리오",
            "바위",
            "사진",
            "아빠",
            "자동차",
            "차키",
            "카레",
            "타조",
            "파랑",
            "하늘",
            "엄마",
            "잠자리",
            "오리",
            "고양이",
            "공주",
            "분홍",
            "베게",
            "이불",
            "계란",
            "포도",
            "사과",
            "별",
            "곰",
            "류소연",
            "어린이",
            "유치원",
            "봄",
            "여름",
            "가을",
            "겨울",
            "돼지",
            "양",
            "우유",
            "양파",
            "크레용",
            "비",
            "우산",
            "김치",
            "할머니",
            "바다",
            "제주도",
            "원숭이",
        )

    fun getRandomWord(): String {
        return words[(0 until words.size).random()]
    }
}