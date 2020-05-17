package com.alicja.chatapp.model

import java.sql.Timestamp

class ChatMessage (val id : String, val text: String, val fromId:String, val toId:String, val timestamp: String) {
    constructor() : this("", "", "", "", "")
}