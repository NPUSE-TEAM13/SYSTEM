package com.nwpu.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nwpu.util.ResourceUtil;

@Controller
@RequestMapping("/VerificationCode")
public class VerificationCodeController extends HttpServlet{

	private static final long serialVersionUID = 1L;

    /**
     * ������������session������
     */
    private static final String SESSION_KEY_OF_RAND_CODE = "randCode"; // todo Ҫͳһ����

    /**
     * 
     */
    private static final int count = 200;

    /**
     * ����ͼ�δ�С(��)
     */
    private static final int width = 105;
    /**
     * ����ͼ�δ�С(��)
     */
    private static final int height = 35;
    /**
     * �����ߵĳ���=1.414*lineWidth
     */
    private static final int lineWidth = 1;

    @RequestMapping(value = "/generate", method = { RequestMethod.POST,
            RequestMethod.GET })
    public void VerificationCode( HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            IOException {
            // ����ҳ�治����
                response.setHeader("Pragma", "No-cache");
                response.setHeader("Cache-Control", "no-cache");
                response.setDateHeader("Expires", 0);
                // response.setContentType("image/png");
                // ���ڴ��д���ͼ��
                final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                // ��ȡͼ��������
                final Graphics2D graphics = (Graphics2D) image.getGraphics();

                // �趨������ɫ
                graphics.setColor(Color.WHITE); // ---1.Color.WHITEΪ��ɫ
                graphics.fillRect(0, 0, width, height);//�������
                // �趨�߿���ɫ
                //graphics.setColor(getRandColor(100, 200)); // ---2.��������������������ɫ����ɫģʽ��ָʹ�����ֻ�ɫ���졢�̡�����ͨ��������ɫ�ĵ����ó�����������ɫ�������ֻ�ɫ��ֵ��ΧΪ0��255
                graphics.drawRect(0, 0, width - 1, height - 1);


                final Random random = new Random();
                // ������������ߣ�ʹͼ���е���֤�벻�ױ���������̽�⵽
                for (int i = 0; i < count; i++) {
                    graphics.setColor(getRandColor(150, 200)); // ---3.

                    final int x = random.nextInt(width - lineWidth - 1) + 1; // ��֤���ڱ߿�֮��
                    final int y = random.nextInt(height - lineWidth - 1) + 1;
                    final int xl = random.nextInt(lineWidth);
                    final int yl = random.nextInt(lineWidth);
                    graphics.drawLine(x, y, x + xl, y + yl);
                }
                // ȡ�����������֤��(4λ����)
                final String resultCode = exctractRandCode();
                for (int i = 0; i < resultCode.length(); i++) {
                    // ����֤����ʾ��ͼ����,���ú�����������ɫ��ͬ����������Ϊ����̫�ӽ�������ֻ��ֱ������
                    // graphics.setColor(new Color(20 + random.nextInt(130), 20 + random
                    // .nextInt(130), 20 + random.nextInt(130)));

                    // ����������ɫ
                    graphics.setColor(Color.BLACK);
                    // ����������ʽ
                    //graphics.setFont(new Font("Arial Black", Font.ITALIC, 18));
                    graphics.setFont(new Font("Times New Roman", Font.BOLD, 24));
                    // �����ַ����ַ���࣬�ϱ߾�
                    System.out.print(resultCode.charAt(i));
                    graphics.drawString(String.valueOf(resultCode.charAt(i)), (23 * i) + 8, 26);
                }
                System.out.println("ֱ�������"+resultCode);
                // ����֤�����SESSION
                request.getSession().setAttribute(SESSION_KEY_OF_RAND_CODE, resultCode);
                // ͼ����Ч
                graphics.dispose();

                // ���ͼ��ҳ��
                ImageIO.write(image, "JPEG", response.getOutputStream());           
    }


    /**
     * @return �����
     */
    private String exctractRandCode() {
        final String randCodeType = ResourceUtil.getRandCodeType();
        int randCodeLength = Integer.parseInt(ResourceUtil.getRandCodeLength());
        if (randCodeType == null) {
            return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
        } else {
            switch (randCodeType.charAt(0)) {
            case '1':
                return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
            case '2':
                return RandCodeImageEnum.LOWER_CHAR.generateStr(randCodeLength);
            case '3':
                return RandCodeImageEnum.UPPER_CHAR.generateStr(randCodeLength);
            case '4':
                return RandCodeImageEnum.LETTER_CHAR.generateStr(randCodeLength);
            case '5':
                return RandCodeImageEnum.ALL_CHAR.generateStr(randCodeLength);

            default:
                return RandCodeImageEnum.NUMBER_CHAR.generateStr(randCodeLength);
            }
        }
    }




    /**
     * ���������ݸ������������ɲ�ͬ����ɫ
     * @param ��������������������ɫ����ɫģʽ��ָʹ�����ֻ�ɫ���졢�̡�����ͨ��������ɫ�ĵ����ó�����������ɫ�������ֻ�ɫ��ֵ��ΧΪ0��255
     * @param ��������������������ɫ����ɫģʽ��ָʹ�����ֻ�ɫ���졢�̡�����ͨ��������ɫ�ĵ����ó�����������ɫ�������ֻ�ɫ��ֵ��ΧΪ0��255
     * @return ������������ɫ
     */
    private Color getRandColor(int fc, int bc) { // ȡ�ø�����Χ�����ɫ
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }

        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);

        return new Color(r, g, b);
    }



    /**
     * ��֤�븨����
     */
    enum RandCodeImageEnum {
        /**
         * ����ַ���
         */
        ALL_CHAR("0123456789abcdefghijkmnpqrstaffvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), // ȥ��Сд��l��o����������������ֵ��ַ���
        /**
         * �ַ�
         */
        LETTER_CHAR("abcdefghijklmnopqrstaffvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"),
        /**
         * Сд��ĸ
         */
        LOWER_CHAR("abcdefghijklmnopqrstaffvwxyz"),
        /**
         * ����
         */
        NUMBER_CHAR("0123456789"),
        /**
         * ��д�ַ�
         */
        UPPER_CHAR("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        /**
         * �����ɵ��ַ���
         */
        private String charStr;

        /**
         * @param charStr
         */
        private RandCodeImageEnum(final String charStr) {
            this.charStr = charStr;
        }

        /**
         * ���������֤��
         * 
         * @param codeLength
         *            ��֤��ĳ���
         * @return ��֤��
         */
        public String generateStr(final int codeLength) {
            final StringBuffer sb = new StringBuffer();
            final Random random = new Random();
            final String sourseStr = getCharStr();

            for (int i = 0; i < codeLength; i++) {
                sb.append(sourseStr.charAt(random.nextInt(sourseStr.length())));
            }

            return sb.toString();
        }

        /**
         * @return the {@link #charStr}
         */
        public String getCharStr() {
            return charStr;
        }

    }
}
