//���ڻ�ȡXMLHttpRequest����ĺ���
function getajaxHttp() {
	// ���屣��XMLHttpRequest����ı���
	var xmlHttp;
	try {
		// �������ñ�׼��ʽ��ȡXMLHttpRequest����
		// ������Firefox, Opera 8.0+, Safari�������
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		// ��������΢���Զ��巽ʽ��ȡXMLHttpRequest����������Internet Explorer�ض��汾
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				// ��������΢���Զ��巽ʽ����ȡXMLHttpRequest����������Internet Explorer�ض��汾
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("�����������֧��AJAX��");
				return false;
			}
		}
	}
	// ���ػ�ȡ���Ķ���
	return xmlHttp;
}
// Ajax�������������󽫷��������صĽ��������ͨ�ı����ɻص���������
// ����url:��Ҫ�����URL��ַ�����ڲ�ͬ������Ի���Ĵ���ʽ��ͬ�����鱾��ַ��ĩβ���һ�����ֵ��������֤ÿ�������ܷ��͵�������
// ����methodtype:��������ʱ�õ����󷽷������õ���get��post�����ַ�����ʽ����
// ����contype:�������ͣ�true��ʾ�첽��false��ʾͬ�����ڶ�̬����JS����ȹ���ʱ��Ҫͬ������
// ����parameter:��Ҫ���������з��͸��������ĸ�����������"������=����ֵ&������=����ֵ..."����ʽ���֣���ʹ��post��ʽ����ʱʹ��
// ����functionName������ɹ�����õĻص�����
// ����obj:���ݸ��ص������ĸ�������
// ����errorFunctionName���������û�гɹ�ִ�е�����Զ��ص��ĺ��������Դ���null
function txtAjaxRequest(url, methodtype, contype, parameter, functionName, obj,
		errorFunctionName) {
	// ��ȡXmlHttpRequest�������Է������󲢻�ȡ���
	var xmlhttp = getajaxHttp();
	// ����ص�
	xmlhttp.onreadystatechange = function() {
		// �������Ѿ��ɹ����ص������ִ��
		if (xmlhttp.readyState == 4) {
			// ������󷵻ص�״̬��Ϊ200����ʾ����ɹ�ִ��
			if (xmlhttp.status == 200) {
				// ���ô��ݹ����Ļص��������������ؽ��������ͨ�ı����ݸ��ص�����
				functionName(xmlhttp.responseText, obj);
			} else {
				// �������û��˳��ִ�У����쳣����ص�������Ϊ�յ�����£����øûص�
				if (errorFunctionName != null) {
					errorFunctionName(url);
				}
			}
		}
	};
	// ����http����
	xmlhttp.open(methodtype, url, contype);
	// ��������
	xmlhttp.send(parameter);
}

// Ajax�������������󽫷��������صĽ������xml dom���ɻص���������
// ����url:��Ҫ�����URL��ַ�����ڲ�ͬ������Ի���Ĵ���ʽ��ͬ�����鱾��ַ��ĩβ���һ�����ֵ��������֤ÿ�������ܷ��͵�������
// ����methodtype:��������ʱ�õ����󷽷������õ���get��post�����ַ�����ʽ����
// ����contype:�������ͣ�true��ʾ�첽��false��ʾͬ�����ڶ�̬����JS����ȹ���ʱ��Ҫͬ������
// ����parameter:��Ҫ���������з��͸��������ĸ�����������"������=����ֵ&������=����ֵ..."����ʽ���֣���ʹ��post��ʽ����ʱʹ��
// ����functionName������ɹ�����õĻص�����
// ����obj:���ݸ��ص������ĸ�������
// ����errorFunctionName���������û�гɹ�ִ�е�����Զ��ص��ĺ��������Դ���null
function xmlAjaxRequest(url, methodtype, contype, parameter, functionName, obj,
		errorFunctionName) {
	// ��ȡXmlHttpRequest�������Է������󲢻�ȡ���
	var xmlhttp = getajaxHttp();
	// ����ص�
	xmlhttp.onreadystatechange = function() {
		// �������Ѿ��ɹ����ص������ִ��
		if (xmlhttp.readyState == 4) {
			// ������󷵻ص�״̬��Ϊ200����ʾ����ɹ�ִ��
			if (xmlhttp.status == 200) {
				// ���ô��ݹ����Ļص��������������ؽ��������ͨ�ı����ݸ��ص�����
				functionName(xmlhttp.responseXML, obj);
			} else {
				// �������û��˳��ִ�У����쳣����ص�������Ϊ�յ�����£����øûص�
				if (errorFunctionName != null) {
					errorFunctionName(url);
				}
			}
		}
	};
	// ����http����
	xmlhttp.open(methodtype, url, contype);
	// ��������
	xmlhttp.send(parameter);
}
