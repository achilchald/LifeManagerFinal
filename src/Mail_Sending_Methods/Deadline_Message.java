package Mail_Sending_Methods;




public class Deadline_Message {
    Mail_Sender sender = new Mail_Sender();


    public void Send_Message(long time,String mail,String price,String domain,String date)
    {

        String Body ="<!doctype html>\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\t<head>\n" +
                "\t\t<!--[if gte mso 15]>\n" +
                "\t\t<xml>\n" +
                "\t\t\t<o:OfficeDocumentSettings>\n" +
                "\t\t\t<o:AllowPNG/>\n" +
                "\t\t\t<o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "\t\t\t</o:OfficeDocumentSettings>\n" +
                "\t\t</xml>\n" +
                "\t\t<![endif]-->\n" +
                "\t\t<meta charset=\"UTF-8\">\n" +
                "        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "\t\t<title>Ανανέωση του Domain, Hosting</title>\n" +
                "        \n" +
                "    <style type=\"text/css\">\n" +
                "\t\tp{\n" +
                "\t\t\tmargin:10px 0;\n" +
                "\t\t\tpadding:0;\n" +
                "\t\t}\n" +
                "\t\ttable{\n" +
                "\t\t\tborder-collapse:collapse;\n" +
                "\t\t}\n" +
                "\t\th1,h2,h3,h4,h5,h6{\n" +
                "\t\t\tdisplay:block;\n" +
                "\t\t\tmargin:0;\n" +
                "\t\t\tpadding:0;\n" +
                "\t\t}\n" +
                "\t\timg,a img{\n" +
                "\t\t\tborder:0;\n" +
                "\t\t\theight:auto;\n" +
                "\t\t\toutline:none;\n" +
                "\t\t\ttext-decoration:none;\n" +
                "\t\t}\n" +
                "\t\tbody,#bodyTable,#bodyCell{\n" +
                "\t\t\theight:100%;\n" +
                "\t\t\tmargin:0;\n" +
                "\t\t\tpadding:0;\n" +
                "\t\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\t.mcnPreviewText{\n" +
                "\t\t\tdisplay:none !important;\n" +
                "\t\t}\n" +
                "\t\t#outlook a{\n" +
                "\t\t\tpadding:0;\n" +
                "\t\t}\n" +
                "\t\timg{\n" +
                "\t\t\t-ms-interpolation-mode:bicubic;\n" +
                "\t\t}\n" +
                "\t\ttable{\n" +
                "\t\t\tmso-table-lspace:0pt;\n" +
                "\t\t\tmso-table-rspace:0pt;\n" +
                "\t\t}\n" +
                "\t\t.ReadMsgBody{\n" +
                "\t\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\t.ExternalClass{\n" +
                "\t\t\twidth:100%;\n" +
                "\t\t}\n" +
                "\t\tp,a,li,td,blockquote{\n" +
                "\t\t\tmso-line-height-rule:exactly;\n" +
                "\t\t}\n" +
                "\t\ta[href^=tel],a[href^=sms]{\n" +
                "\t\t\tcolor:inherit;\n" +
                "\t\t\tcursor:default;\n" +
                "\t\t\ttext-decoration:none;\n" +
                "\t\t}\n" +
                "\t\tp,a,li,td,body,table,blockquote{\n" +
                "\t\t\t-ms-text-size-adjust:100%;\n" +
                "\t\t\t-webkit-text-size-adjust:100%;\n" +
                "\t\t}\n" +
                "\t\t.ExternalClass,.ExternalClass p,.ExternalClass td,.ExternalClass div,.ExternalClass span,.ExternalClass font{\n" +
                "\t\t\tline-height:100%;\n" +
                "\t\t}\n" +
                "\t\ta[x-apple-data-detectors]{\n" +
                "\t\t\tcolor:inherit !important;\n" +
                "\t\t\ttext-decoration:none !important;\n" +
                "\t\t\tfont-size:inherit !important;\n" +
                "\t\t\tfont-family:inherit !important;\n" +
                "\t\t\tfont-weight:inherit !important;\n" +
                "\t\t\tline-height:inherit !important;\n" +
                "\t\t}\n" +
                "\t\t.templateContainer{\n" +
                "\t\t\tmax-width:600px !important;\n" +
                "\t\t}\n" +
                "\t\ta.mcnButton{\n" +
                "\t\t\tdisplay:block;\n" +
                "\t\t}\n" +
                "\t\t.mcnImage,.mcnRetinaImage{\n" +
                "\t\t\tvertical-align:bottom;\n" +
                "\t\t}\n" +
                "\t\t.mcnTextContent{\n" +
                "\t\t\tword-break:break-word;\n" +
                "\t\t}\n" +
                "\t\t.mcnTextContent img{\n" +
                "\t\t\theight:auto !important;\n" +
                "\t\t}\n" +
                "\t\t.mcnDividerBlock{\n" +
                "\t\t\ttable-layout:fixed !important;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Background Style\n" +
                "\t@tip Set the background color and top border for your email. You may want to choose colors that match your company's branding.\n" +
                "\t*/\n" +
                "\t\tbody,#bodyTable{\n" +
                "\t\t\t/*@editable*/background-color:#fff;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Background Style\n" +
                "\t@tip Set the background color and top border for your email. You may want to choose colors that match your company's branding.\n" +
                "\t*/\n" +
                "\t\t#bodyCell{\n" +
                "\t\t\t/*@editable*/border-top:0;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Email Border\n" +
                "\t@tip Set the border for your email.\n" +
                "\t*/\n" +
                "\t\t.templateContainer{\n" +
                "\t\t\t/*@editable*/border:0;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Heading 1\n" +
                "\t@tip Set the styling for all first-level headings in your emails. These should be the largest of your headings.\n" +
                "\t@style heading 1\n" +
                "\t*/\n" +
                "\t\th1{\n" +
                "\t\t\t/*@editable*/color:#1A202E;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:48px;\n" +
                "\t\t\t/*@editable*/font-style:normal;\n" +
                "\t\t\t/*@editable*/font-weight:bold;\n" +
                "\t\t\t/*@editable*/line-height:125%;\n" +
                "\t\t\t/*@editable*/letter-spacing:normal;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Heading 2\n" +
                "\t@tip Set the styling for all second-level headings in your emails.\n" +
                "\t@style heading 2\n" +
                "\t*/\n" +
                "\t\th2{\n" +
                "\t\t\t/*@editable*/color:#c69c62;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:30px;\n" +
                "\t\t\t/*@editable*/font-style:normal;\n" +
                "\t\t\t/*@editable*/font-weight:bold;\n" +
                "\t\t\t/*@editable*/line-height:125%;\n" +
                "\t\t\t/*@editable*/letter-spacing:normal;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Heading 3\n" +
                "\t@tip Set the styling for all third-level headings in your emails.\n" +
                "\t@style heading 3\n" +
                "\t*/\n" +
                "\t\th3{\n" +
                "\t\t\t/*@editable*/color:#505050;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:26px;\n" +
                "\t\t\t/*@editable*/font-style:normal;\n" +
                "\t\t\t/*@editable*/font-weight:bold;\n" +
                "\t\t\t/*@editable*/line-height:125%;\n" +
                "\t\t\t/*@editable*/letter-spacing:normal;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Page\n" +
                "\t@section Heading 4\n" +
                "\t@tip Set the styling for all fourth-level headings in your emails. These should be the smallest of your headings.\n" +
                "\t@style heading 4\n" +
                "\t*/\n" +
                "\t\th4{\n" +
                "\t\t\t/*@editable*/color:#505050;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:22px;\n" +
                "\t\t\t/*@editable*/font-style:normal;\n" +
                "\t\t\t/*@editable*/font-weight:bold;\n" +
                "\t\t\t/*@editable*/line-height:125%;\n" +
                "\t\t\t/*@editable*/letter-spacing:normal;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Preheader\n" +
                "\t@section Preheader Style\n" +
                "\t@tip Set the background color and borders for your email's preheader area.\n" +
                "\t*/\n" +
                "\t\t#templatePreheader{\n" +
                "\t\t\t/*@editable*/background-color:#c69c62;\n" +
                "\t\t\t/*@editable*/border-top:0;\n" +
                "\t\t\t/*@editable*/border-bottom:0;\n" +
                "\t\t\t/*@editable*/padding-top:9px;\n" +
                "\t\t\t/*@editable*/padding-bottom:9px;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Preheader\n" +
                "\t@section Preheader Text\n" +
                "\t@tip Set the styling for your email's preheader text. Choose a size and color that is easy to read.\n" +
                "\t*/\n" +
                "\t\t#templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/color:#2e2020;\n" +
                "\t\t\t/*@editable*/font-family:Helvetica;\n" +
                "\t\t\t/*@editable*/font-size:12px;\n" +
                "\t\t\t/*@editable*/line-height:150%;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Preheader\n" +
                "\t@section Preheader Link\n" +
                "\t@tip Set the styling for your email's preheader links. Choose a color that helps them stand out from your text.\n" +
                "\t*/\n" +
                "\t\t#templatePreheader .mcnTextContent a,#templatePreheader .mcnTextContent p a{\n" +
                "\t\t\t/*@editable*/color:#8b0000;\n" +
                "\t\t\t/*@editable*/font-weight:normal;\n" +
                "\t\t\t/*@editable*/text-decoration:underline;\n" +
                "\t\t}\n" +
                "\t\t#templateHeader,#templateBody{\n" +
                "\t\t\tbackground-color:#FFFFFF;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Header\n" +
                "\t@section Header Text\n" +
                "\t@tip Set the styling for your email's header text. Choose a size and color that is easy to read.\n" +
                "\t*/\n" +
                "\t\t#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/color:#1A202E;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:16px;\n" +
                "\t\t\t/*@editable*/line-height:150%;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Header\n" +
                "\t@section Header Link\n" +
                "\t@tip Set the styling for your email's header links. Choose a color that helps them stand out from your text.\n" +
                "\t*/\n" +
                "\t\t#templateHeader .mcnTextContent a,#templateHeader .mcnTextContent p a{\n" +
                "\t\t\t/*@editable*/color:#1A202E;\n" +
                "\t\t\t/*@editable*/font-weight:normal;\n" +
                "\t\t\t/*@editable*/text-decoration:underline;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Body\n" +
                "\t@section Body Text\n" +
                "\t@tip Set the styling for your email's body text. Choose a size and color that is easy to read.\n" +
                "\t*/\n" +
                "\t\t#templateBody .mcnTextContent,#templateBody .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/color:#505050;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:16px;\n" +
                "\t\t\t/*@editable*/line-height:150%;\n" +
                "\t\t\t/*@editable*/text-align:left;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Body\n" +
                "\t@section Body Link\n" +
                "\t@tip Set the styling for your email's body links. Choose a color that helps them stand out from your text.\n" +
                "\t*/\n" +
                "\t\t#templateBody .mcnTextContent a,#templateBody .mcnTextContent p a{\n" +
                "\t\t\t/*@editable*/color:#1A202E;\n" +
                "\t\t\t/*@editable*/font-weight:normal;\n" +
                "\t\t\t/*@editable*/text-decoration:underline;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Footer\n" +
                "\t@section Footer Style\n" +
                "\t@tip Set the background color and borders for your email's footer area.\n" +
                "\t*/\n" +
                "\t\t#templateFooter{\n" +
                "\t\t\t/*@editable*/background-color:#c69c62;\n" +
                "\t\t\t/*@editable*/border-top:0;\n" +
                "\t\t\t/*@editable*/border-bottom:0;\n" +
                "\t\t\t/*@editable*/padding-top:9px;\n" +
                "\t\t\t/*@editable*/padding-bottom:9px;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Footer\n" +
                "\t@section Footer Text\n" +
                "\t@tip Set the styling for your email's footer text. Choose a size and color that is easy to read.\n" +
                "\t*/\n" +
                "\t\t#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/color:#FFFFFF;\n" +
                "\t\t\t/*@editable*/font-family:Jura;\n" +
                "\t\t\t/*@editable*/font-size:12px;\n" +
                "\t\t\t/*@editable*/line-height:150%;\n" +
                "\t\t\t/*@editable*/text-align:center;\n" +
                "\t\t}\n" +
                "\t/*\n" +
                "\t@tab Footer\n" +
                "\t@section Footer Link\n" +
                "\t@tip Set the styling for your email's footer links. Choose a color that helps them stand out from your text.\n" +
                "\t*/\n" +
                "\t\t#templateFooter .mcnTextContent a,#templateFooter .mcnTextContent p a{\n" +
                "\t\t\t/*@editable*/color:#FFFFFF;\n" +
                "\t\t\t/*@editable*/font-weight:normal;\n" +
                "\t\t\t/*@editable*/text-decoration:underline;\n" +
                "\t\t}\n" +
                "\t@media only screen and (min-width:768px){\n" +
                "\t\t.templateContainer{\n" +
                "\t\t\twidth:600px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\tbody,table,td,p,a,li,blockquote{\n" +
                "\t\t\t-webkit-text-size-adjust:none !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\tbody{\n" +
                "\t\t\twidth:100% !important;\n" +
                "\t\t\tmin-width:100% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t#bodyCell{\n" +
                "\t\t\tpadding-top:10px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnRetinaImage{\n" +
                "\t\t\tmax-width:100% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImage{\n" +
                "\t\t\twidth:100% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnCartContainer,.mcnCaptionTopContent,.mcnRecContentContainer,.mcnCaptionBottomContent,.mcnTextContentContainer,.mcnBoxedTextContentContainer,.mcnImageGroupContentContainer,.mcnCaptionLeftTextContentContainer,.mcnCaptionRightTextContentContainer,.mcnCaptionLeftImageContentContainer,.mcnCaptionRightImageContentContainer,.mcnImageCardLeftTextContentContainer,.mcnImageCardRightTextContentContainer,.mcnImageCardLeftImageContentContainer,.mcnImageCardRightImageContentContainer{\n" +
                "\t\t\tmax-width:100% !important;\n" +
                "\t\t\twidth:100% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnBoxedTextContentContainer{\n" +
                "\t\t\tmin-width:100% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImageGroupContent{\n" +
                "\t\t\tpadding:9px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnCaptionLeftContentOuter .mcnTextContent,.mcnCaptionRightContentOuter .mcnTextContent{\n" +
                "\t\t\tpadding-top:9px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImageCardTopImageContent,.mcnCaptionBottomContent:last-child .mcnCaptionBottomImageContent,.mcnCaptionBlockInner .mcnCaptionTopContent:last-child .mcnTextContent{\n" +
                "\t\t\tpadding-top:18px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImageCardBottomImageContent{\n" +
                "\t\t\tpadding-bottom:9px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImageGroupBlockInner{\n" +
                "\t\t\tpadding-top:0 !important;\n" +
                "\t\t\tpadding-bottom:0 !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImageGroupBlockOuter{\n" +
                "\t\t\tpadding-top:9px !important;\n" +
                "\t\t\tpadding-bottom:9px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnTextContent,.mcnBoxedTextContentColumn{\n" +
                "\t\t\tpadding-right:18px !important;\n" +
                "\t\t\tpadding-left:18px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcnImageCardLeftImageContent,.mcnImageCardRightImageContent{\n" +
                "\t\t\tpadding-right:18px !important;\n" +
                "\t\t\tpadding-bottom:0 !important;\n" +
                "\t\t\tpadding-left:18px !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t\t.mcpreview-image-uploader{\n" +
                "\t\t\tdisplay:none !important;\n" +
                "\t\t\twidth:100% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Heading 1\n" +
                "\t@tip Make the first-level headings larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\th1{\n" +
                "\t\t\t/*@editable*/font-size:45px !important;\n" +
                "\t\t\t/*@editable*/line-height:125% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Heading 2\n" +
                "\t@tip Make the second-level headings larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\th2{\n" +
                "\t\t\t/*@editable*/font-size:20px !important;\n" +
                "\t\t\t/*@editable*/line-height:125% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Heading 3\n" +
                "\t@tip Make the third-level headings larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\th3{\n" +
                "\t\t\t/*@editable*/font-size:18px !important;\n" +
                "\t\t\t/*@editable*/line-height:125% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Heading 4\n" +
                "\t@tip Make the fourth-level headings larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\th4{\n" +
                "\t\t\t/*@editable*/font-size:16px !important;\n" +
                "\t\t\t/*@editable*/line-height:150% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Boxed Text\n" +
                "\t@tip Make the boxed text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n" +
                "\t*/\n" +
                "\t\t.mcnBoxedTextContentContainer .mcnTextContent,.mcnBoxedTextContentContainer .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/font-size:14px !important;\n" +
                "\t\t\t/*@editable*/line-height:150% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Preheader Visibility\n" +
                "\t@tip Set the visibility of the email's preheader on small screens. You can hide it to save space.\n" +
                "\t*/\n" +
                "\t\t#templatePreheader{\n" +
                "\t\t\t/*@editable*/display:block !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Preheader Text\n" +
                "\t@tip Make the preheader text larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\t#templatePreheader .mcnTextContent,#templatePreheader .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/font-size:14px !important;\n" +
                "\t\t\t/*@editable*/line-height:150% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Header Text\n" +
                "\t@tip Make the header text larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\t#templateHeader .mcnTextContent,#templateHeader .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/font-size:16px !important;\n" +
                "\t\t\t/*@editable*/line-height:150% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Body Text\n" +
                "\t@tip Make the body text larger in size for better readability on small screens. We recommend a font size of at least 16px.\n" +
                "\t*/\n" +
                "\t\t#templateBody .mcnTextContent,#templateBody .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/font-size:16px !important;\n" +
                "\t\t\t/*@editable*/line-height:150% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}\t@media only screen and (max-width: 480px){\n" +
                "\t/*\n" +
                "\t@tab Mobile Styles\n" +
                "\t@section Footer Text\n" +
                "\t@tip Make the footer content text larger in size for better readability on small screens.\n" +
                "\t*/\n" +
                "\t\t#templateFooter .mcnTextContent,#templateFooter .mcnTextContent p{\n" +
                "\t\t\t/*@editable*/font-size:14px !important;\n" +
                "\t\t\t/*@editable*/line-height:150% !important;\n" +
                "\t\t}\n" +
                "\n" +
                "}</style></head>\n" +
                "    <body>\n" +
                "        <!--*|IF:MC_PREVIEW_TEXT|*-->\n" +
                "        <!--[if !gte mso 9]><!----><span class=\"mcnPreviewText\" style=\"display:none; font-size:0px; line-height:0px; max-height:0px; max-width:0px; opacity:0; overflow:hidden; visibility:hidden; mso-hide:all;\">Χρόνια Πολλά!</span><!--<![endif]-->\n" +
                "        <!--*|END:IF|*-->\n" +
                "        <center>\n" +
                "            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" height=\"100%\" width=\"100%\" id=\"bodyTable\">\n" +
                "                <tr>\n" +
                "                    <td align=\"center\" valign=\"top\" id=\"bodyCell\">\n" +
                "                        <!-- BEGIN TEMPLATE // -->\n" +
                "\t\t\t\t\t\t<!--[if gte mso 9]>\n" +
                "\t\t\t\t\t\t<table align=\"center\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t<td align=\"center\" valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t\t\t<![endif]-->\n" +
                "                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"templateContainer\">\n" +
                "                            <tr>\n" +
                "                                <td valign=\"top\" id=\"templatePreheader\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnTextBlock\" style=\"min-width:100%;\">\n" +
                "    <tbody class=\"mcnTextBlockOuter\">\n" +
                "        <tr>\n" +
                "            <td valign=\"top\" class=\"mcnTextBlockInner\" style=\"padding-top:9px;\">\n" +
                "              \t<!--[if mso]>\n" +
                "\t\t\t\t<table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "\t\t\t    \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t<td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"max-width:100%; min-width:100%;\" width=\"100%\" class=\"mcnTextContentContainer\">\n" +
                "                    <tbody><tr>\n" +
                "                        \n" +
                "                        <td valign=\"top\" class=\"mcnTextContent\" style=\"padding: 0px 18px 9px; text-align: center;\">\n" +
                "                        \n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "</table></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td valign=\"top\" id=\"templateHeader\"><table class=\"mcnTextBlock\" style=\"min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "    <tbody class=\"mcnTextBlockOuter\">\n" +
                "        <tr>\n" +
                "            <td class=\"mcnTextBlockInner\" style=\"padding-top:9px;\" valign=\"top\">\n" +
                "              \t<!--[if mso]>\n" +
                "\t\t\t\t<table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "\t\t\t    \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t<td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                <table style=\"max-width:100%; min-width:100%;\" class=\"mcnTextContentContainer\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">\n" +
                "                    <tbody><tr>\n" +
                "                        \n" +
                "                        <td class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\" valign=\"top\">\n" +
                "                        \n" +
                "                            <h4 style=\"text-align: center;\"><br>\n" +
                "Ανανέωση του Domain, Hosting <br>\n" +
                domain +
                "</h4>\n" +
                "\n" +
                "<h5 style=\"text-align: center;\">Ημ/νία Λήξης: " +
                date +
                "11:59PM UTC </h5>\n" +
                "\n" +
                "<p style=\"text-align: center;\">&nbsp;</p>\n" +
                "\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "</table></td>\n" +
                "                            </tr>\n" +
                "                            <tr>\n" +
                "                                <td valign=\"top\" id=\"templateBody\"><table class=\"mcnTextBlock\" style=\"min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "    <tbody class=\"mcnTextBlockOuter\">\n" +
                "        <tr>\n" +
                "            <td class=\"mcnTextBlockInner\" style=\"padding-top:9px;\" valign=\"top\">\n" +
                "              \t<!--[if mso]>\n" +
                "\t\t\t\t<table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "\t\t\t    \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t<td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                <table style=\"max-width:100%; min-width:100%;\" class=\"mcnTextContentContainer\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">\n" +
                "                    <tbody><tr>\n" +
                "                        \n" +
                "                        <td class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\" valign=\"top\">\n" +
                "                        \n" +
                "\t\t\t\t\t\t\t<p style=\"text-align: center; font-size:13px;\">\n" +
                "\t\t\t\t\t\t\t\tΤο παρακάτω όνομα χώρου πλησιάζει στην λήξη της περιόδου εκχώρησής του. <br>\n" +
                "\t\t\t\t\t\t\t\tΑνανέωση του ονόματος χώρου μπορεί να πραγματοποιηθεί μέσω καταχωρητή. <br>\n" +
                "\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\tΠαρακαλώ όπως κάνετε την κατάθεση των χρημάτων στον παρακάτω λογαριασμό.<br>\n" +
                "\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\t<strong>VIVA WALLET</strong><br>\n" +
                "\t\t\t\t\t\t\t\tIBAN: GR8970100000000100971672601<br>\n" +
                "\t\t\t\t\t\t\t\tBIC / SWIFT: VPAYGRAA<br>\n" +
                "\t\t\t\t\t\t\t\tΔΙΚΑΙΟΥΧΟΣ: Νίκος Μπιζάς<br>\n" +
                "\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\tΤο κόστος για την ανανέωση είναι: <strong>" +
                price +
                "</strong><br> \n" +
                "\t\t\t\t\t\t\t\t<br>\n" +
                "\t\t\t\t\t\t\t\tΠαρακαλώ μετά την κατάθεση των χρημάτων θα πρέπει να μας ενημερώσετε ηλεκτρονικά για την ενημέρωση της καρτέλας σας.<br>\n" +
                "&nbsp;</p>\n" +
                "\n" +
                "<p style=\"text-align: center; font-size:13px; color:#e70303;\">*Σε περίπτωση που δεν ανανεώσετε το όνομα μέχρι την αναγραφόμενη ημερομηνία, η σελίδα σας θα διαγραφεί από το μητρώο και θα χαθούν όλα τα δεδομένα. Σε περίπτωση επαναφοράς θα υπάρξει έξτρα κόστος.<br>\n" +
                "&nbsp;</p>\n" +
                "\n" +
                "<p style=\"text-align: center;\"><strong>Μπιζάς Νίκος</strong></p>\n" +
                "\n" +
                "<p style=\"text-align: center;\"><strong><img data-file-id=\"2241465\" src=\"https://clients.casperweb.gr/wp-content/uploads/2020/09/logo.png\" style=\"border: 0px  ; width: 291px; height:78px; margin: 0px;\" width=\"120\" height=\"120\"></strong><br>\n" +
                "<font color=\"#000000\"><a href=\"https://www.facebook.com/casperweb.gr/\" target=\"_blank\" rel=\"noopener noreferrer\"><img alt=\".Casper Web. Facebook Page\" shrinktofit=\"true\" src=\"https://clients.casperweb.gr/wp-content/uploads/2020/09/facebook.png\" style=\"border:none;\" border=\"0\"><a href=\"https://www.instagram.com/casper_web/\" target=\"_blank\" rel=\"noopener noreferrer\"><img alt=\".Casper Web. on Instagram\" shrinktofit=\"true\" src=\"https://clients.casperweb.gr/wp-content/uploads/2020/09/instagram.png\" style=\"border:none;\" border=\"0\"></a>&nbsp;<br>\n" +
                "Email: <a href=\"mailto:nikos@casperweb.gr\" target=\"_blank\" rel=\"noopener noreferrer\">nikos@casperweb.gr</a> | Τηλέφωνο <a href=\"tel:00306946461057\">694 6461057</a><br />Website: <a href=\"https://www.casperweb.gr\" target=\"_blank\" rel=\"noopener noreferrer\">www.casperweb.gr</a></p>\n" +
                "\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "</table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" class=\"mcnDividerBlock\" style=\"min-width:100%;\">\n" +
                "    <tbody class=\"mcnDividerBlockOuter\">\n" +
                "        <tr>\n" +
                "            <td class=\"mcnDividerBlockInner\" style=\"min-width: 100%; padding: 20px 18px 0px;\">\n" +
                "                <table class=\"mcnDividerContent\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"min-width:100%;\">\n" +
                "                    <tbody><tr>\n" +
                "                        <td>\n" +
                "                            <span></span>\n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "<!--            \n" +
                "                <td class=\"mcnDividerBlockInner\" style=\"padding: 18px;\">\n" +
                "                <hr class=\"mcnDividerContent\" style=\"border-bottom-color:none; border-left-color:none; border-right-color:none; border-bottom-width:0; border-left-width:0; border-right-width:0; margin-top:0; margin-right:0; margin-bottom:0; margin-left:0;\" />\n" +
                "-->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "</table></td>\n" +
                "                            </tr>\n" +
                "\t\t\t\t\t\t\t<tr>\n" +
                "\t\t\t\t\t\t\t\t<td valign=\"bottom\">\n" +
                "\t\t\t\t\t\t\t\t\t<img src=\"https://clients.casperweb.gr/wp-content/uploads/2020/09/footer_bg.jpg\" height=\"288\" width=\"600\" class=\"mcnImage\" style=\"display:block;\">\n" +
                "\t\t\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t\t</tr>\n" +
                "                            <tr>\n" +
                "                                <td valign=\"top\" id=\"templateFooter\"><table class=\"mcnTextBlock\" style=\"min-width:100%;\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\">\n" +
                "    <tbody class=\"mcnTextBlockOuter\">\n" +
                "        <tr>\n" +
                "            <td class=\"mcnTextBlockInner\" style=\"padding-top:9px;\" valign=\"top\">\n" +
                "              \t<!--[if mso]>\n" +
                "\t\t\t\t<table align=\"left\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" width=\"100%\" style=\"width:100%;\">\n" +
                "\t\t\t\t<tr>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "\t\t\t    \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t<td valign=\"top\" width=\"600\" style=\"width:600px;\">\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                <table style=\"max-width:100%; min-width:100%;\" class=\"mcnTextContentContainer\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"left\">\n" +
                "                    <tbody><tr>\n" +
                "                        \n" +
                "                        <td class=\"mcnTextContent\" style=\"padding-top:0; padding-right:18px; padding-bottom:9px; padding-left:18px;\" valign=\"top\">\n" +
                "                        \n" +
                "                           \n" +
                "                        </td>\n" +
                "                    </tr>\n" +
                "                </tbody></table>\n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</td>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "                \n" +
                "\t\t\t\t<!--[if mso]>\n" +
                "\t\t\t\t</tr>\n" +
                "\t\t\t\t</table>\n" +
                "\t\t\t\t<![endif]-->\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "    </tbody>\n" +
                "</table></td>\n" +
                "                            </tr>\n" +
                "                        </table>\n" +
                "\t\t\t\t\t\t<!--[if gte mso 9]>\n" +
                "\t\t\t\t\t\t</td>\n" +
                "\t\t\t\t\t\t</tr>\n" +
                "\t\t\t\t\t\t</table>\n" +
                "\t\t\t\t\t\t<![endif]-->\n" +
                "                        <!-- // END TEMPLATE -->\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "            </table>\n" +
                "        </center>\n" +
                "    </body>\n" +
                "</html>";



        if (time<30 && time>10)
        {
            sender.SendMail(mail,"Domain expires soon",Body);
        }

        if (time<10)
        {
            sender.SendMail(mail,"Domain about to expire",Body);
        }
    }

}

