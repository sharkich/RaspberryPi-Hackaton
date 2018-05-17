#!/bin/bash

wpa_sup="/etc/wpa_supplicant/wpa_supplicant.conf"

echo -e "NETM WPA Supplicant Creation Script\r\n"
# Read username
echo -n "Please enter your KT e-mail address: "
read kt_username
echo ""
# Read password
echo -n "Please enter your KT password (will not be echoed): "
read -s password
echo ""
echo -n "Please enter your KT password again (will not be echoed): "
read -s password2
echo ""

# Verify passwords are the same
if [ "$password" == "$password2" ]; then
    echo "Passwords match, moving ahead."
else
    echo "Passwords do not match, please try again."
    exit 1
fi

hash_password=`echo -n $password | iconv -t utf16le | openssl md4 | awk '{print $2}'`

echo "Hashed password = $hash_password"

if grep -Fxq "#NETM" $wpa_sup
then
 echo "NETM section already exists within $wpa_sup."
 echo "Replacing identity and password values."
 sed -i "/.*#NETM-ID/c\identity=\"$kt_username\" #NETM-ID" $wpa_sup
 sed -i "/.*#NETM-PW/c\password=hash:$hash_password #NETM-PW" $wpa_sup
 exit 1
else
 echo "No pre-existing NETM section in $wpa_sup, moving ahead."
fi

export kt_username hash_password

envsubst <<EOF >> $wpa_sup
#NETM

network={
ssid="NETM"
proto=RSN
key_mgmt=WPA-EAP
eap=PEAP
phase2="auth=MSCHAPV2"
identity="$kt_username" #NETM-ID

# While insecure in the event the hash doesn't work,
# you can remove the comment below and enter your clear
# text password and comment out the hash version and try
# again.
#
#password="clear-text-passwd-here"
password=hash:$hash_password #NETM-PW
}
EOF
