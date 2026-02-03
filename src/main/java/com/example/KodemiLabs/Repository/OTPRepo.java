package com.example.KodemiLabs.Repository;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.example.KodemiLabs.Model.OTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OTPRepo {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;

    public void save(OTP otp) {
        dynamoDBMapper.save(otp);
    }

    public OTP getOtpData(String userId) {
        return dynamoDBMapper.load(OTP.class, userId);
    }

    public void delete(OTP otp) {
        dynamoDBMapper.delete(otp);
    }
}
