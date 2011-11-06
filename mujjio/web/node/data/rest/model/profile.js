var Base = require('./base').Base;

var Profile = exports.Profile = function (request) { 

    this.data = {}; 

    this.data.aboutMe = {
    	value : request.aboutMe,
    	type : String,
    	validations : []
    },

    this.data.bodyType = {
    	value : request.bodyType,
    	type : String,
    	validations : []
    },

    this.data.drinker = {
    	value : request.drinker,
    	type : String,
    	validations : []
    },

    this.data.fashion = {
    	value : request.fashion,
    	type : String,
    	validations : []
    },

    this.data.happiestWhen = {
    	value : request.happiestWhen,
    	type : String,
    	validations : []
    },

    this.data.humour = {
    	value : request.humour,
    	type : String,
    	validations : []
    },

    this.data.livingArrangement = {
    	value : request.livingArrangement,
    	type : String,
    	validations : []
    },

    this.data.lookingFor = {
    	value : request.lookingFor,
    	type : String,
    	validations : []
    },

    this.data.relationshipStatus = {
    	value : request.relationshipStatus,
    	type : String,
    	validations : []
    }

    this.data.religion = {
    	value : request.religion,
    	type : String,
    	validations : []
    },

    this.data.smoker = {
    	value : request.smoker,
    	type : String,
    	validatations : []
    },

    this.data.scaredOf = {
    	value : request.scaredOf,
    	type :  String,
    	validations : []
    },

    this.age = {
    	value : request.age,
    	type : Number,
    	validations : []
    }

    this.data.created = new Date();
    return this;    
}

Profile.prototype.__proto__ = Base.prototype;
Profile.prototype.constructor = Profile
Profile.prototype.className = "profile";