document.addEventListener('DOMContentLoaded', init);

function init() {
    const workspaceId = document.body.dataset.workspaceId;

    if (!workspaceId) {
        console.error('워크스페이스 ID를 찾을 수 없습니다.');
        return;
    }

    fetchWorks(workspaceId);
    fetchDatas(workspaceId);
}

async function fetchWorks(id){
    const worksUL = document.getElementById('works');
    if(!worksUL)    return;

    try{
        const resp = await fetch(`/api/workspaces/${id}/works`);
        if(!resp.ok){
            throw new Error(`Fetch fail works`);
        }
        const works = await resp.json();
        renderList(worksUL, works, renderWorkItem);
    }catch (error){
        console.error("fetchWorks Error", error);
        return;
    }
}

const renderWorkItem = (item) => {
    const li = document.createElement('li');
    const a = document.createElement('a');
    a.href = `/works/${item.id}`;
    a.innerText = item.originalFileName;
    const div = document.createElement('div');
    const p_storedFileName = document.createElement('p');
    p_storedFileName.innerText = item.storedFileName;
    const p_uploadTime = document.createElement('p');
    p_uploadTime.innerText = item.uploadTime;
    div.appendChild(p_storedFileName);
    div.appendChild(p_uploadTime);
    a.appendChild(div);
    li.appendChild(a);

    return li;
}

async function fetchDatas(id){
    const worksUL = document.getElementById('datas');
    if(!worksUL)    return;

    try{
        const resp = await fetch(`/api/workspaces/${id}/datas`);
        if(!resp.ok){
            throw new Error(`Fetch fail data`);
        }
        const works = await resp.json();
        renderList(worksUL, works, renderDataItem);
    }catch (error){
        console.error("fetchWorks Error", error);
        return;
    }
}

const renderDataItem = (item) => {
    const li = document.createElement('li');
    const a = document.createElement('a');
    a.href = `/datas/${item.id}`;
    a.innerText = item.originalFileName;
    const div = document.createElement('div');
    const p_storedFileName = document.createElement('p');
    p_storedFileName.innerText = item.storedFileName;
    const p_uploadTime = document.createElement('p');
    p_uploadTime.innerText = item.uploadTime;
    div.appendChild(p_storedFileName);
    div.appendChild(p_uploadTime);
    a.appendChild(div);
    li.appendChild(a);

    return li;
}

function renderList(ulElement, items, renderItem) {
    if (!items || items.length === 0) {
        ulElement.innerHTML = `<li>EMPTY</li>`;
        return;
    }

    const fragment = document.createDocumentFragment();
    items.forEach(item => {
        const li = renderItem(item)
        fragment.appendChild(li);
    });

    ulElement.appendChild(fragment);
}






