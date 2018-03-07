package com.example.demo.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "member")
public class Member {

    @Id
    private int tid;
    private Integer fid;
    private Integer grade;
    private String famLeader;
    private String relation;
    private String memberName;
    private String memberNameDistinct;
    private String memberNameEn;
    private String memberNameHan;
    private String rrnId1;
    private String rrnId2;
    private String birthYear;
    private String birthDate;
    private String birthSolar;
    private String memberSex;
    private String bloodType;
    private String memberHomepage;
    private String memberPhone;
    private String memberCellphone;
    private String memberEmail;
    private Integer pGrade;
    private String pGradeName;
    private String pGradeDate;
    private String pGradeMinister;
    private String pGradeChurch;
    private String pGradeOrd;
    private String guide;
    private String guide2;
    private String guide_phone;
    private String guide_relation;
    private String regiDate;
    private String receptionDate;
    private String wedding;
    private String weddingAnniversary;
    private String christening;
    private String christeningName;
    private String christeningDate;
    private String christeningChurch;
    private String christeningMinister;
    private String christeningOrd;
    private String lastChurch;
    private String lastGrade;
    private String lastAct;
    private String memberZipcode;
    private String memberAddress1;
    private String memberAddress2;
    private String memberAddressStreet;
    private String memberZipcodeStreet;
    private String job;
    private String jobTypeName;
    private String jobType;
    private String careerName;
    private String career;
    private String companyName;
    private String companyPosition;
    private String companyPhone;
    private String schoolType;
    private String schoolName;
    private String schoolYear;
    private String schoolGrade;
    private String schoolMajor;
    private String schoolClass;
    private String carNumber;
    private String carType;
    private String parkingPermit;
    private String carMemo;
    private String etc;
    private String etcSecure;
    private String memberImage;
//    private String inputDate;
    private String registerId;
    private String registerName;
    private String lastView;
    private String modifierId;
    private String modifierName;
    private String etc1;
    private String etc2;
    private String etc3;
    private String etc4;
    private String etc5;
    private String etc6;
    private String etc7;
    private String etc8;
    private String etc9;
    private String removed;
//    private String cDepth1;
//    private String cDepth2;
//    private String cDepth1Name;
//    private String cDepth2Name;
//    private String cGroupUpdateDate;
    private String partnerName;
    private String partnerTid;
    private String parentName1;
    private String parentPhone1;
    private String parentName2;
    private String parentPhone2;
    private String fidBefore;
    private String relationBefore;
    private String lastVisit;
    private String withFamily;
    private String interest;


}
